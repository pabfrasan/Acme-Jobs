
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.StatusApplication;
import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
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
		/*
		 * if (request.isMethod(HttpMethod.GET)) {
		 * Integer JobId = new Integer(request.getServletRequest().getParameter("JobId"));
		 * 
		 * // model.setAttribute("accept", "false");
		 * model.setAttribute("JobId", JobId);
		 * } else {
		 * request.transfer(model, "pending");
		 * }
		 */
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		Principal principal = request.getPrincipal();
		int workerId = principal.getAccountId();
		Worker worker = this.repository.findWorkerById(workerId);

		int JobId = request.getModel().getInteger("JobId");
		Job job = this.repository.findOneJobById(JobId);

		result = new Application();
		result.setMoment(moment);
		StatusApplication status = StatusApplication.PENDING;
		result.setStatus(status);
		result.setWorker(worker);
		result.setJob(job);

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

		/*
		 * int id;
		 * id = new Integer(request.getServletRequest().getParameter("id"));
		 */
		/*
		 * int idA;
		 * idA = request.getPrincipal().getActiveRoleId();
		 * /*
		 * Job job = this.repository.findOneJobById(id);
		 * entity.setJob(job);
		 */
		Job j;

		int JobId = request.getModel().getInteger("JobId");
		j = this.repository.findOneJobById(JobId);

		/*
		 * Worker worker = this.repository.findWorkerById(idA);
		 * entity.setWorker(worker);
		 */

		this.repository.save(entity);
	}

}
