
package acme.features.authenticated.auditRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.auditRecords.AuditRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/audit-record/")
public class AuthenticatedAuditRecordController extends AbstractController<Authenticated, AuditRecord> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedAuditRecordListCorrespondingService	listCorrespondingService;

	@Autowired
	private AuthenticatedAuditRecordShowService					showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_CORRESPONDING, BasicCommand.LIST, this.listCorrespondingService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
