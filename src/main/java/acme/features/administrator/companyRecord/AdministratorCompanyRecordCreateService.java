
package acme.features.administrator.companyRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCompanyRecordCreateService implements AbstractCreateService<Administrator, CompanyRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorCompanyRecordRepository repository;


	// AbstractListService<Administrator,CompanyRecord > interface --------------------

	@Override
	public boolean authorise(final Request<CompanyRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CompanyRecord> request, final CompanyRecord entity, final Errors error) {
		assert request != null;
		assert entity != null;
		assert error != null;

		request.bind(entity, error);
	}

	@Override
	public void unbind(final Request<CompanyRecord> request, final CompanyRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "ceo", "description", "webSite", "phone", "email", "isIncorporated", "numberStars");
	}

	@Override
	public CompanyRecord instantiate(final Request<CompanyRecord> request) {
		CompanyRecord result = new CompanyRecord();
		result.setIsIncorporated(false);
		return result;
	}

	@Override
	public void validate(final Request<CompanyRecord> request, final CompanyRecord entity, final Errors error) {
		assert request != null;
		assert entity != null;
		assert error != null;
	}

	@Override
	public void create(final Request<CompanyRecord> request, final CompanyRecord entity) {
		this.repository.save(entity);
	}
}
