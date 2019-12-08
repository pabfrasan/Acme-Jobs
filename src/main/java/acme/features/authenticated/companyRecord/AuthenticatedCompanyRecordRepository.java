
package acme.features.authenticated.companyRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedCompanyRecordRepository extends AbstractRepository {

	@Query("select a from CompanyRecord a where a.id =?1")
	CompanyRecord findOnebyId(int id);

	@Query("select a from CompanyRecord a")
	Collection<CompanyRecord> findManyAll();

}
