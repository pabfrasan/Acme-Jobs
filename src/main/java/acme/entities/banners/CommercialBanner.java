
package acme.entities.banners;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.entities.creditCards.CreditCard;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommercialBanner extends Banner {

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@ManyToOne(optional = true)
	@Valid
	private CreditCard creditCard;
}
