
package acme.features.authenticated.offer;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.offers.Offer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedOfferRepository extends AbstractRepository {

	@Query("select a from Offer a where a.id =?1")
	Offer findOnebyId(int id);

	@Query("select a from Offer a")
	Collection<Offer> findManyAll();
}
