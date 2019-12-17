
package acme.features.employer.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customizationParameters.CustomizationParameter;
import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	// Internal state ---------------------------

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "reference", "moreInfo", "salary", "status", "deadline", "descriptor");

		Collection<Descriptor> descriptors = this.repository.findAllDescriptors();
		model.setAttribute("descriptors", descriptors);

		if (entity.getDescriptor() != null) {
			model.setAttribute("idDescriptor", entity.getDescriptor().getId() + "");
		} else {
			model.setAttribute("idDescriptor", "0");
		}
	}

	@Override
	public Job instantiate(final Request<Job> request) {
		assert request != null;
		Job result;

		result = new Job();
		result.setStatus("DRAFT");

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean esFinal;

		if (!errors.hasErrors("deadline")) {
			Date hoy = new Date();
			esFinal = hoy.before(entity.getDeadline()) && entity.getStatus() == "PUBLISHED";
		} else {
			esFinal = false;
		}

		if (esFinal) {

			String stringId = (String) request.getModel().getAttribute("idDescriptor");
			int id = Integer.parseInt(stringId);
			Descriptor descriptor = this.repository.findDescriptorById(id);

			Collection<Duty> duties = descriptor.getDuties();
			double sum = 0;

			for (Duty d : duties) {
				sum = sum + d.getPercentage();
			}
			errors.state(request, sum == 100.00, "status", "employer.job.error.status.sum100");

			List<CustomizationParameter> customs = new ArrayList<>(this.repository.findAllCustomizationParameter());
			CustomizationParameter custom = customs.get(0);
			String[] spamEn = custom.getSpamWordsEn().split(",");
			String[] spamEs = custom.getSpamWordsEs().split(",");

			for (Duty d : duties) {
				long numSpamEn = 0;
				long numSpamEs = 0;

				for (String s : spamEn) {
					if (d.getDescription().contains(s)) {
						numSpamEn = numSpamEn + 1;
					}
				}
				errors.state(request, numSpamEn < custom.getThreshold(), "status", "employer.job.error.status.spamEn");

				for (String s : spamEs) {
					if (d.getDescription().contains(s)) {
						numSpamEs = numSpamEs + 1;
					}
				}
				errors.state(request, numSpamEs < custom.getThreshold(), "status", "employer.job.error.status.spamEs");
			}
		}

		Model model = request.getModel();
		Collection<Descriptor> descriptors = this.repository.findAllDescriptors();
		model.setAttribute("descriptors", descriptors);
		request.setModel(model);
	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		String stringId = (String) request.getModel().getAttribute("idDescriptor");

		Integer desId = new Integer(stringId);

		if (desId != 0) {
			Descriptor descriptor = this.repository.findDescriptorById(desId);
			entity.setDescriptor(descriptor);
		}
		Employer employer = this.repository.findEmployerById(request.getPrincipal().getActiveRoleId());
		entity.setEmployer(employer);
		this.repository.save(entity);
	}
}
