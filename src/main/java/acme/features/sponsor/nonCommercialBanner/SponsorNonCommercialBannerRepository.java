
package acme.features.sponsor.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.customizationParameters.CustomizationParameter;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select c from NonCommercialBanner c")
	Collection<NonCommercialBanner> findManyAll();

	@Query("select c from NonCommercialBanner c where c.id = ?1")
	NonCommercialBanner findById(int id);

	@Query("select c from NonCommercialBanner c where c.sponsor.id = ?1")
	Collection<NonCommercialBanner> findManyBySponsorId(int id);

	@Query("select s from Sponsor s where s.id = ?1")
	Sponsor findSponsorById(int id);

	@Query("select c from CustomizationParameter c")
	Collection<CustomizationParameter> findAllCustomizationParameter();
}
