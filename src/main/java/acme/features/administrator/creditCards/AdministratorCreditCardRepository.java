
package acme.features.administrator.creditCards;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.creditCards.CreditCard;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCreditCardRepository extends AbstractRepository {

	@Query("select c from CreditCard c")
	Collection<CreditCard> findManyAll();

	@Query("select c from CreditCard c where c.id = ?1")
	CreditCard findById(int id);

	@Query("select co.creditCard from CommercialBanner co where co.id = ?1")
	CreditCard findByCommercialBannerId(int id);

	@Query("select b.creditCard from CommercialBanner b where b.id = ?1")
	Collection<CreditCard> findManyCorrespondingByBannerId(Integer jobId);
}
