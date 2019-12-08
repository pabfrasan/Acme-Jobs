
package acme.features.employer.descriptor;

import org.springframework.data.jpa.repository.Query;

import acme.entities.descriptors.Descriptor;
import acme.framework.repositories.AbstractRepository;

public interface EmployerDescriptorRepository extends AbstractRepository {

	@Query("select d from Descriptor d where d.id =?1")
	Descriptor findOnebyId(int id);

}
