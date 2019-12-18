
package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorAuditRecordShowService implements AbstractShowService<Auditor, AuditRecord> {
	// Internal state ---------------------------------------------------------

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		//		boolean result;
		//		int auditRecordId;
		//
		//		Principal principal;
		//		Auditor auditor;
		//		AuditRecord auditRecord;
		//
		//		auditRecordId = request.getModel().getInteger("id");
		//		auditRecord = this.repository.findOneAuditRecordById(auditRecordId);
		//		auditor = auditRecord.getAuditor();
		//		principal = request.getPrincipal();
		//		result = auditor.getUserAccount().getId() == principal.getAccountId();
		//
		//		return result;
		return true;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "moment", "body");
	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;

		AuditRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditRecordById(id);

		return result;
	}
}
