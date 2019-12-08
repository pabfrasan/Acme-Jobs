
package acme.features.authenticated.request;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.Request_;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRequestRepository extends AbstractRepository {

	@Query("select r from Request_ r where r.id =?1")
	Request_ findOnebyId(int id);

	@Query("select r from Request_ r")
	Collection<Request_> findManyAll();
}
