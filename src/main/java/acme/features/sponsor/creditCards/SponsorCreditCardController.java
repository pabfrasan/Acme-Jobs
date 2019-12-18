
package acme.features.sponsor.creditCards;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/credit-card/")
public class SponsorCreditCardController extends AbstractController<Sponsor, CreditCard> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorCreditCardListCorrespondingService	listCorrespondingService;

	@Autowired
	private SponsorCreditCardShowService				showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_CORRESPONDING, BasicCommand.LIST, this.listCorrespondingService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
