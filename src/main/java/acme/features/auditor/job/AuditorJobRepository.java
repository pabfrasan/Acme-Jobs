
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select distinct a.job from AuditRecord a where a.auditor.id = ?1")
	Collection<Job> findManyWrittenByAuditorId(int auditorId);

	@Query("select j from Job j")
	Collection<Job> findAllJob();

	@Query("select a from AuditRecord a")
	Collection<AuditRecord> findAllAuditRecord();
}
