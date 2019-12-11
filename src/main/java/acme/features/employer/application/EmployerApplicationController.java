
package acme.features.employer.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/application/")
public class EmployerApplicationController extends AbstractController<Employer, Application> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private EmployerApplicationListMineService		listMineService;

	@Autowired
	private EmployerApplicationListReferenceService	listReferenceService;

	@Autowired
	private EmployerApplicationListStatusService	listStatusService;

	@Autowired
	private EmployerApplicationListMomentService	listMomentService;

	@Autowired
	private EmployerApplicationShowService			showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addCustomCommand(CustomCommand.LIST_REFERENCE, BasicCommand.LIST, this.listReferenceService);
		super.addCustomCommand(CustomCommand.LIST_STATUS, BasicCommand.LIST, this.listStatusService);
		super.addCustomCommand(CustomCommand.LIST_MOMENT, BasicCommand.LIST, this.listMomentService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
