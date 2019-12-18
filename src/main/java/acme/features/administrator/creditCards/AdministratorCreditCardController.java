
package acme.features.administrator.creditCards;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.creditCards.CreditCard;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/credit-card/")
public class AdministratorCreditCardController extends AbstractController<Administrator, CreditCard> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorCreditCardListCorrespondingService	listCorrespondingService;

	@Autowired
	private AdministratorCreditCardShowService				showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_CORRESPONDING, BasicCommand.LIST, this.listCorrespondingService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
