
package acme.features.sponsor.nonCommercialBanner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.customizationParameters.CustomizationParameter;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorNonCommercialBannerUpdateService implements AbstractUpdateService<Sponsor, NonCommercialBanner> {

	@Autowired
	SponsorNonCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<NonCommercialBanner> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "targetUrl", "jingle", "slogan");

	}

	@Override
	public NonCommercialBanner findOne(final Request<NonCommercialBanner> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		NonCommercialBanner result;

		result = this.repository.findById(id);
		return result;
	}

	@Override
	public void validate(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
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
			} else if (entity.getJingle().contains(s)) {
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
			} else if (entity.getJingle().contains(s)) {
				numSpam = numSpam + 1;
			}
		}
		errors.state(request, numSpam < custom.getThreshold(), "slogan", "sponsor.commercial-banner.error.slogan.spam");
	}

	@Override
	public void update(final Request<NonCommercialBanner> request, final NonCommercialBanner entity) {
		assert request != null;
		this.repository.save(entity);
	}

}
