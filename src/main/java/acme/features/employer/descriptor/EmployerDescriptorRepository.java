
package acme.features.employer.descriptor;

import org.springframework.data.jpa.repository.Query;

import acme.entities.descriptors.Descriptor;
import acme.framework.repositories.AbstractRepository;

public interface EmployerDescriptorRepository extends AbstractRepository {

	@Query("select j.descriptor from Job j where j.id =?1")
	Descriptor findOnebyJobId(int id);

}
