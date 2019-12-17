
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedMessageThreadRemoveUser implements AbstractUpdateService<Authenticated, MessageThread> {

	@Autowired
	private AuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "moment", "messages");

	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "users");

	}

	@Override
	public MessageThread findOne(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result;

		int threadId = new Integer(request.getServletRequest().getParameter("threadId"));
		result = this.repository.findOneById(threadId);
		return result;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		UserAccount user = this.repository.findUserByUserName(request.getServletRequest().getParameter("userremove"));
		int threadId = new Integer(request.getServletRequest().getParameter("threadId"));
		MessageThread m = this.repository.findOneById(threadId);
		Boolean existe = m.getUsers().contains(user);
		errors.state(request, existe, "userremove", "messageThread.error.remove");

	}

	@Override
	public void update(final Request<MessageThread> request, final MessageThread entity) {
		assert request != null;
		assert entity != null;
		UserAccount user;

		user = this.repository.findUserByUserName(request.getServletRequest().getParameter("userremove"));
		Collection<UserAccount> users = entity.getUsers();
		users.remove(user);
		entity.setUsers(users);
		this.repository.save(entity);

	}

}
