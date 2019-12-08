
package acme.features.authenticated.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Request_;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedRequestShowService implements AbstractShowService<Authenticated, Request_> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedRequestRepository repository;


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

		request.unbind(entity, model, "ticker", "title", "moment", "deadline", "text", "reward");
	}

	@Override
	public Request_ findOne(final Request<Request_> request) {
		assert request != null;

		Request_ result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findOnebyId(id);

		return result;
	}

}
