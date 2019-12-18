
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.auditRecords.AuditRecord;
import acme.entities.customizationParameters.CustomizationParameter;
import acme.entities.descriptors.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.employer.id = ?1")
	Collection<Job> findManyByEmployerId(int employerId);

	@Query("select e from Employer e where e.id = ?1")
	Employer findEmployerById(int id);

	@Query("select a from Application a where a.job.id = ?1")
	Collection<Application> findApplicationsByJobId(int id);

	@Query("select a from AuditRecord a where a.job.id = ?1")
	Collection<AuditRecord> findAuditRecordByJobId(int id);

	@Query("select d from Descriptor d")
	Collection<Descriptor> findAllDescriptors();

	@Query("select d from Descriptor d where d.id = ?1")
	Descriptor findDescriptorById(int id);

	@Query("select c from CustomizationParameter c")
	Collection<CustomizationParameter> findAllCustomizationParameter();

	@Query("select j from Job j")
	Collection<Job> findAllJobs();

	@Query("Select j from Job j where j.reference= ?1")
	Job findOneReference(String reference);
}
