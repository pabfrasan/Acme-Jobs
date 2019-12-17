
package acme.features.authenticated.userAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedUserAccountShowService implements AbstractShowService<Authenticated, UserAccount> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedUserAccountRepository repository;


	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;

		//		boolean result;
		//		Principal principal = request.getPrincipal();
		//		Integer threadId = request.getModel().getInteger("threadId");
		//
		//		UserAccount prin = this.repository.findOneUserAccountById(request.getPrincipal().getAccountId());
		//		Collection<UserAccount> usuarios = this.repository.usersOfThread(threadId);
		//
		//		result = principal.isAuthenticated() && usuarios.contains(prin);
		//
		return true;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "username");
	}

	@Override
	public UserAccount findOne(final Request<UserAccount> request) {
		assert request != null;

		UserAccount result;

		result = this.repository.findOneUserAccountById(request.getModel().getInteger("id"));

		return result;
	}

}
