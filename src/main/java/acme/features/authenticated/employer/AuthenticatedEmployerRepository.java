
package acme.features.authenticated.employer;

import org.springframework.data.jpa.repository.Query;

import acme.entities.roles.Employer;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedEmployerRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select e from Employer e where e.userAccount.id = ?1")
	Employer findOneEmployerByUserAccountId(int id);
}
