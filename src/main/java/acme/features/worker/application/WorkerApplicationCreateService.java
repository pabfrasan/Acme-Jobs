
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	// Internal state ---------------------------

	@Autowired
	WorkerApplicationRepository repository;


	// AbstractCreateService<Worker, Application> interface --------------------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "status", "justification");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "statement", "skills", "qualifications");

		if (request.isMethod(HttpMethod.GET)) {
			Integer jobId = new Integer(request.getServletRequest().getParameter("jobId"));

			model.setAttribute("jobId", jobId);
		}
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		result = new Application();
		result.setMoment(moment);
		result.setStatus("PENDING");

		return result;

	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		entity.setMoment(moment);

		Job j;

		int jobId = new Integer(request.getServletRequest().getParameter("jobId"));
		j = this.repository.findOneJobById(jobId);

		entity.setJob(j);

		Worker w = this.repository.findWorkerById(request.getPrincipal().getActiveRoleId());

		entity.setWorker(w);

		this.repository.save(entity);
	}

}
