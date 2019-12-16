
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

		request.bind(entity, errors, "moment", "status", "justification", "job", "worker");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;

		request.unbind(entity, model, "reference", "statement", "skills", "qualifications");
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;

		result = new Application();

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

		int id;
		id = new Integer(request.getServletRequest().getParameter("id"));

		int idA;
		idA = request.getPrincipal().getAccountId();

		StatusApplication status = StatusApplication.PENDING;

		Job job = this.repository.findOneJobById(id);

		Worker worker = this.repository.findOneWorkerByUserAccountId(idA);

		entity.setMoment(moment);
		entity.setStatus(status);
		entity.setWorker(worker);
		entity.setJob(job);
		entity.setJustification("");
		this.repository.save(entity);
	}

}
