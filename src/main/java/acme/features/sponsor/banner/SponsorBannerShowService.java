
package acme.features.sponsor.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorBannerShowService implements AbstractShowService<Sponsor, Banner> {

	@Autowired
	SponsorBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		boolean result;
		Principal principal = request.getPrincipal();
		Integer bannerId = request.getModel().getInteger("id");
		Integer owner = this.repository.getSponsorId(bannerId);

		result = principal.getActiveRoleId() == owner;

		return result;
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (entity instanceof CommercialBanner) {
			request.unbind(entity, model, "picture", "slogan", "targetUrl", "creditCard");
		} else {
			request.unbind(entity, model, "picture", "slogan", "targetUrl", "jingle");
		}
	}

	@Override
	public Banner findOne(final Request<Banner> request) {
		assert request != null;

		Banner result;

		result = this.repository.findOneById(request.getModel().getInteger("id"));

		return result;
	}
}
