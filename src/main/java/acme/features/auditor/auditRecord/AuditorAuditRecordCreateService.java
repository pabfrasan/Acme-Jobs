
package acme.features.auditor.auditRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, AuditRecord> {

	// Internal state ---------------------------

	@Autowired
	AuditorAuditRecordRepository repository;


	// AbstractCreateService<Auditor, AuditRecord> interface --------------------

	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "body");

		if (request.isMethod(HttpMethod.GET)) {
			Integer jobId = new Integer(request.getServletRequest().getParameter("jobId"));

			model.setAttribute("jobId", jobId);
		}
	}

	@Override
	public AuditRecord instantiate(final Request<AuditRecord> request) {
		assert request != null;

		AuditRecord result;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		result = new AuditRecord();
		result.setMoment(moment);

		return result;

	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		entity.setMoment(moment);

		Job j;

		int jobId = new Integer(request.getServletRequest().getParameter("jobId"));
		j = this.repository.findOneJobById(jobId);

		entity.setJob(j);

		Auditor w = this.repository.findAuditorById(request.getPrincipal().getActiveRoleId());

		entity.setAuditor(w);

		this.repository.save(entity);
	}

}
