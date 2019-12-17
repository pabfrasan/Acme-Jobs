
package acme.features.authenticated.messageThread;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.messageThreads.MessageThread;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/message-thread/")
public class AuthenticatedMessageThreadController extends AbstractController<Authenticated, MessageThread> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private AuthenticatedMessageThreadAddUserService	addUserService;

	@Autowired
	private AuthenticatedMessageThreadCreateService		createService;

	@Autowired
	private AuthenticatedMessageThreadListMineService	listMineService;

	@Autowired
	private AuthenticatedMessageThreadShowService		showService;

	@Autowired
	private AuthenticatedMessageThreadRemoveUser		removeUserService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addCustomCommand(CustomCommand.ADD_USER, BasicCommand.UPDATE, this.addUserService);
		super.addCustomCommand(CustomCommand.REMOVE_USER, BasicCommand.UPDATE, this.removeUserService);
	}
}
