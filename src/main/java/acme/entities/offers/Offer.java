
package acme.entities.offers;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Offer extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)

	private Date				deadline;

	@NotBlank
	private String				text;

	@NotNull
	@Valid
	private Money				maxMoney;

	@NotNull
	@Valid
	private Money				minMoney;

	@NotBlank
	@Pattern(regexp = "^O[a-zA-Z]{4}-[0-9]{5}$")
	@Column(unique = true)
	private String				ticker;
	// Derived attributes -----------------------------------------------------

	private String				rango;


	public String getRango() {
		this.rango = this.minMoney.toString() + " - " + this.maxMoney;
		return this.rango;
	}
	// Relationships ----------------------------------------------------------

}
