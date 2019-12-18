
package acme.features.sponsor.creditCards;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class SponsorCreditCardListCorrespondingService implements AbstractListService<Sponsor, CreditCard> {

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
		assert model != null;
		assert entity != null;

		request.unbind(entity, model, "holderName", "brandName");

	}

	@Override
	public Collection<CreditCard> findMany(final Request<CreditCard> request) {
		assert request != null;

		Collection<CreditCard> result;
		Integer id;

		id = new Integer(request.getServletRequest().getParameter("id"));
		result = this.repository.findManyCorrespondingByBannerId(id);

		return result;
	}

}
