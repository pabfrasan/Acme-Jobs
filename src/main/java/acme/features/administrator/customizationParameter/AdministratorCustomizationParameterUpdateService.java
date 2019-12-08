
package acme.features.administrator.customizationParameter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customizationParameters.CustomizationParameter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCustomizationParameterUpdateService implements AbstractUpdateService<Administrator, CustomizationParameter> {

	@Autowired
	AdministratorCustomizationParameterRepository repository;


	@Override
	public boolean authorise(final Request<CustomizationParameter> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CustomizationParameter> request, final CustomizationParameter entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CustomizationParameter> request, final CustomizationParameter entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamWordsEn", "spamWordsEs", "threshold");
	}

	@Override
	public CustomizationParameter findOne(final Request<CustomizationParameter> request) {
		assert request != null;

		List<CustomizationParameter> lista = new ArrayList<CustomizationParameter>(this.repository.findManyAll());

		CustomizationParameter result = lista.get(0);

		return result;
	}

	@Override
	public void validate(final Request<CustomizationParameter> request, final CustomizationParameter entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<CustomizationParameter> request, final CustomizationParameter entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
