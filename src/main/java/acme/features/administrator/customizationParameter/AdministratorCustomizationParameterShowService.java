
package acme.features.administrator.customizationParameter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customizationParameters.CustomizationParameter;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorCustomizationParameterShowService implements AbstractShowService<Administrator, CustomizationParameter> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorCustomizationParameterRepository repository;


	@Override
	public boolean authorise(final Request<CustomizationParameter> request) {
		assert request != null;

		return true;
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
}
