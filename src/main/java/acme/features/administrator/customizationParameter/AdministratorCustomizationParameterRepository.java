
package acme.features.administrator.customizationParameter;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customizationParameters.CustomizationParameter;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCustomizationParameterRepository extends AbstractRepository {

	@Query("select c from CustomizationParameter c")
	Collection<CustomizationParameter> findManyAll();

}
