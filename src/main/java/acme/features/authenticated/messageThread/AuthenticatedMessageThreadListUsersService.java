
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageThreadListUsersService implements AbstractListService<Authenticated, UserAccount> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	// AbstractListService<Administrator, Shout> interface --------------------

	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;

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
	public Collection<UserAccount> findMany(final Request<UserAccount> request) {
		assert request != null;

		Collection<UserAccount> result;
		Integer threadId = new Integer(request.getServletRequest().getParameter("threadId"));
		result = this.repository.usersOfThread(threadId);

		return result;
	}
}
