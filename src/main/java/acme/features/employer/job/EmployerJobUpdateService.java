
package acme.features.employer.job;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {
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
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date hoy = new Date();

		if (!errors.hasErrors("deadline")) {
			boolean esFinal = hoy.before(entity.getDeadline()) && entity.getStatus() == "PUBLISHED";
			errors.state(request, !esFinal, "status", "employer.job.error.status.esFinal");
		}

		if (entity.getStatus() == "PUBLISHED") {
			errors.state(request, entity.getDescriptor() != null, "status", "employer.job.error.status.esFinal");
		}

		Model model = request.getModel();
		Collection<Descriptor> descriptors = this.repository.findAllDescriptors();
		model.setAttribute("descriptors", descriptors);

		request.setModel(model);
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		Job result;

		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		String stringId = (String) request.getModel().getAttribute("idDescriptor");

		Integer desId = new Integer(stringId);

		if (desId != 0) {
			Descriptor descriptor = this.repository.findDescriptorById(desId);
			entity.setDescriptor(descriptor);
		}

		this.repository.save(entity);
	}
}
