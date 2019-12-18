
package acme.entities.messageThreads;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.entities.messages.Message;
import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class MessageThread extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long				serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String							title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date							moment;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotEmpty
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<@Valid UserAccount>	users;

	@NotNull
	@OneToMany
	private Collection<@Valid Message>		messages;
}
