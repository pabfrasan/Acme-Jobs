
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.id = ?1")
	AuditRecord findOneAuditRecordById(int id);

	@Query("select a from AuditRecord a where a.job.id = ?1")
	Collection<AuditRecord> findManyCorrespondingByJobId(Integer jobId);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int jobId);

	@Query("select a from Auditor a where a.id = ?1")
	Auditor findAuditorById(int auditorId);

}
