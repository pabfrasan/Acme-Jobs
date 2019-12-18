
package acme.features.authenticated.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result;

		Principal principal = request.getPrincipal();
		result = principal.isAuthenticated();

		return result;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "tags", "body");

		if (request.isMethod(HttpMethod.GET)) {
			Integer threadId = new Integer(request.getServletRequest().getParameter("threadId"));

			model.setAttribute("accept", "false");
			model.setAttribute("threadId", threadId);
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		Message result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Message();
		result.setTitle("");
		result.setBody("");
		result.setTags("");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted;

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "authenticated.message.error.must-accept");
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		Integer threadId;
		MessageThread mt;
		Message saved;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		saved = this.repository.save(entity);

		threadId = new Integer(request.getServletRequest().getParameter("threadId"));
		mt = this.repository.findMessageThreadById(threadId);

		mt.getMessages().add(saved);

		this.repository.save(mt);
	}

}
