
package acme.features.authenticated.request;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Request_;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedRequestListService implements AbstractListService<Authenticated, Request_> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedRequestRepository repository;


	// AbstractListService<Administrator, Shout> interface --------------------

	@Override
	public boolean authorise(final Request<Request_> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Request_> request, final Request_ entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "deadline", "title");//atributos del modelo al listado(los que apareceran en el listado)
	}

	@Override
	public Collection<Request_> findMany(final Request<Request_> request) {
		assert request != null;

		Collection<Request_> result;

		result = this.repository.findManyAll();

		return result;
	}
}
