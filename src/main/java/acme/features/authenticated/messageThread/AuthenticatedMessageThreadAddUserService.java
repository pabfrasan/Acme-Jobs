/*
 * AuthenticatedMessageThreadUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

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
public class AuthenticatedMessageThreadAddUserService implements AbstractUpdateService<Authenticated, MessageThread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedMessageThreadRepository repository;


	// AbstractUpdateService<Authenticated, MessageThread> interface -----------------

	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int threadId = new Integer(request.getServletRequest().getParameter("threadId"));
		MessageThread m = this.repository.findOneById(threadId);
		UserAccount user = this.repository.findUserByUserName(request.getServletRequest().getParameter("useradd"));
		errors.state(request, user != null, "useradd", "messageThread.error.notnull");
		Boolean noExiste = !m.getUsers().contains(user);

		errors.state(request, noExiste, "useradd", "messageThread.add.error");

	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "users", "title", "moment", "messages");
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
	public void update(final Request<MessageThread> request, final MessageThread entity) {
		assert request != null;
		assert entity != null;
		UserAccount user;

		user = this.repository.findUserByUserName(request.getServletRequest().getParameter("useradd"));
		Collection<UserAccount> users = entity.getUsers();
		users.add(user);
		entity.setUsers(users);
		this.repository.save(entity);
	}

}
