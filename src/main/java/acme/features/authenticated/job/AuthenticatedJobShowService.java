
package acme.features.authenticated.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedJobShowService implements AbstractShowService<Authenticated, Job> {
	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		int jobId;

		Job job;
		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);

		return job.isActive();
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Descriptor descriptor = entity.getDescriptor();

		request.unbind(entity, model, "title", "reference", "moreInfo", "salary", "description", "status", "deadline");
		model.setAttribute("descriptor", descriptor.getDescription());
		Collection<Duty> duties = descriptor.getDuties();
		model.setAttribute("duties", duties);
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
}
