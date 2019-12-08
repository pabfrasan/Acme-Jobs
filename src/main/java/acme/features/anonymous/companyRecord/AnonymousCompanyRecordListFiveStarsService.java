
package acme.features.anonymous.companyRecord;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousCompanyRecordListFiveStarsService implements AbstractListService<Anonymous, CompanyRecord> {

	@Autowired
	AnonymousCompanyRecordRepository repository;


	// AbstractListService<Anonymous,CompanyRecord > interface --------------------

	@Override
	public boolean authorise(final Request<CompanyRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CompanyRecord> request, final CompanyRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "description", "phone", "numberStars");
	}

	@Override
	public Collection<CompanyRecord> findMany(final Request<CompanyRecord> request) {
		assert request != null;

		Collection<CompanyRecord> result;

		result = this.repository.findManyFiveStar();

		Collection<CompanyRecord> res = new HashSet<>();

		for (CompanyRecord cr : result) {

			if (cr.getIsIncorporated() == true) {

				cr.setName(cr.getName() + " Inc.");

			} else {
				cr.setName(cr.getName() + " LLC");

			}

			res.add(cr);
		}

		return result;
	}
}
