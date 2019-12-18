
package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalAnnouncement", "totalCompanyRecord", "totalInvestorRecords", "minRewardsRequest", "maxRewardsRequest", "avgRewardsRequest", "stdRewardsRequest", "minRewardsOffer", "maxRewardsOffer", "avgRewardsOffer",
			"stdRewardsOffer", "avgJobsEmployer", "avgApplicationsEmployer", "avgApplicationsWorker", "ratioJobsByStatus", "ratioApplicationsByStatus", "sectorNumberCompanyRecord", "sectorNumberInvestorRecord", "numberPendingApplications",
			"numberAcceptedApplications", "numberRejectedApplications");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result = new Dashboard();

		List<Integer> acc = new ArrayList<Integer>();
		List<Integer> rej = new ArrayList<Integer>();
		List<Integer> pen = new ArrayList<Integer>();

		Date ahora = new Date();

		result.setTotalAnnouncement(this.repository.getTotalAnnouncement());
		result.setTotalCompanyRecord(this.repository.getTotalCompanyRecord());
		result.setTotalInvestorRecords(this.repository.getTotalInvestorRecord());

		result.setMinRewardsRequest(this.repository.getMinRewardRequest());
		result.setMaxRewardsRequest(this.repository.getMaxRewardRequest());
		result.setAvgRewardsRequest(this.repository.getAvgRewardRequest());
		result.setStdRewardsRequest(this.repository.getStdRewardRequest(result.getAvgRewardsRequest()));

		result.setMinRewardsOffer(this.repository.getMinRewardOffer());
		result.setMaxRewardsOffer(this.repository.getMaxRewardOffer());
		result.setAvgRewardsOffer(this.repository.getAvgRewardOffer());
		result.setStdRewardsOffer(this.repository.getStdRewardOffer(result.getAvgRewardsOffer()));

		result.setAvgJobsEmployer(this.repository.getAvgJobsPerEmployer());
		result.setAvgApplicationsEmployer(this.repository.getAvgApplicationsPerEmployer());
		result.setAvgApplicationsWorker(this.repository.getAvgApplicationsPerWorker());

		result.setSectorNumberCompanyRecord(this.repository.getSectorNumberCompanyRecord());
		result.setSectorNumberInvestorRecord(this.repository.getSectorNumberInvestorRecord());

		result.setRatioJobsByStatus(this.repository.getRatioJobsByStatus());
		result.setRatioApplicationsByStatus(this.repository.getRatioApplicationsByStatus());

		for (int i = 27; i >= 0; i--) {

			Calendar anterior = Calendar.getInstance();
			anterior.setTime(ahora);
			anterior.add(Calendar.DAY_OF_MONTH, -i);

			Integer appAcc = this.repository.getApplicationsByStatus("ACCEPTED", anterior.getTime());

			acc.add(27 - i, appAcc);
			rej.add(27 - i, this.repository.getApplicationsByStatus("REJECTED", anterior.getTime()));
			pen.add(27 - i, this.repository.getApplicationsByStatus("PENDING", anterior.getTime()));
		}
		result.setNumberPendingApplications(pen);
		result.setNumberAcceptedApplications(acc);
		result.setNumberRejectedApplications(rej);

		return result;
	}
}
