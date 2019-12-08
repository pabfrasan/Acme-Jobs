
package acme.features.provider.request;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.requests.Request_;
import acme.entities.roles.Provider;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/provider/request/")
public class ProviderRequestController extends AbstractController<Provider, Request_> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ProviderRequestCreateService createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);

	}
}
