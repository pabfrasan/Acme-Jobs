
package acme.features.sponsor.commercialBanner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.customizationParameters.CustomizationParameter;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCommercialBannerCreateService implements AbstractCreateService<Sponsor, CommercialBanner> {

	@Autowired
	SponsorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "targetUrl", "slogan");
	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		assert request != null;

		CommercialBanner cb = new CommercialBanner();
		return cb;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		List<CustomizationParameter> customs = new ArrayList<>(this.repository.findAllCustomizationParameter());
		CustomizationParameter custom = customs.get(0);
		String[] spamEn = custom.getSpamWordsEn().split(",");
		String[] spamEs = custom.getSpamWordsEs().split(",");
		long numSpam = 0;

		for (String s : spamEn) {
			if (entity.getSlogan().contains(s)) {
				numSpam = numSpam + 1;
			} else if (entity.getPicture().contains(s)) {
				numSpam = numSpam + 1;
			} else if (entity.getTargetUrl().contains(s)) {
				numSpam = numSpam + 1;
			}
		}

		for (String s : spamEs) {
			if (entity.getSlogan().contains(s)) {
				numSpam = numSpam + 1;
			} else if (entity.getPicture().contains(s)) {
				numSpam = numSpam + 1;
			} else if (entity.getTargetUrl().contains(s)) {
				numSpam = numSpam + 1;
			}
		}
		errors.state(request, numSpam < custom.getThreshold(), "slogan", "sponsor.commercial-banner.error.slogan.spam");
	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		Sponsor sponsor = this.repository.findSponsorById(request.getPrincipal().getActiveRoleId());
		entity.setSponsor(sponsor);
		this.repository.save(entity);

	}

}
