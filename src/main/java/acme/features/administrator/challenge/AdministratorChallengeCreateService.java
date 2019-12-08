
package acme.features.administrator.challenge;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "deadline", "rewardGold", "rewardSilver", "rewardBronze");
	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		assert request != null;

		Challenge result = new Challenge();

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date hoy = new Date();

		if (!errors.hasErrors("deadline")) {
			boolean esFuturo = hoy.before(entity.getDeadline());
			errors.state(request, esFuturo, "deadline", "administrator.challenge.error.deadline.esFuturo");
		}

		if (!errors.hasErrors("rewardGold") || !errors.hasErrors("rewardSilver")) {
			boolean esMayor = entity.getRewardGold().getAmount() > entity.getRewardSilver().getAmount();
			errors.state(request, esMayor, "rewardGold", "administrator.challenge.error.rewardGold.esMayor");
		}

		if (!errors.hasErrors("rewardSilver") || !errors.hasErrors("rewardBronze")) {
			boolean esMayor = entity.getRewardSilver().getAmount() > entity.getRewardBronze().getAmount();
			errors.state(request, esMayor, "rewardSilver", "administrator.challenge.error.rewardSilver.esMayor");
		}
	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
