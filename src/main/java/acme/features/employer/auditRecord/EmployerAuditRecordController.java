
package acme.features.employer.auditRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/audit-record/")
public class EmployerAuditRecordController extends AbstractController<Employer, AuditRecord> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private EmployerAuditRecordListCorrespondingService	listCorrespondingService;

	@Autowired
	private EmployerAuditRecordShowService				showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_CORRESPONDING, BasicCommand.LIST, this.listCorrespondingService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
