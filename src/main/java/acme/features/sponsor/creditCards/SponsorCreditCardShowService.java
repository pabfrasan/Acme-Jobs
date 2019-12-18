
package acme.features.sponsor.creditCards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorCreditCardShowService implements AbstractShowService<Sponsor, CreditCard> {

	@Autowired
	SponsorCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "holderName", "brandName", "number", "exMonth", "exYear", "cvv");
	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		int id = new Integer(request.getServletRequest().getParameter("id"));
		CreditCard result;

		result = this.repository.findByCommercialBannerId(id);
		return result;
	}

}