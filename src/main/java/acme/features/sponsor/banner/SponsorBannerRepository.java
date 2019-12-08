
package acme.features.sponsor.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.banners.Banner;
import acme.framework.repositories.AbstractRepository;

public interface SponsorBannerRepository extends AbstractRepository {

	@Query("select b from Banner b where b.id =?1")
	Banner findOneById(Integer id);

	@Query("select b from Banner b where b.sponsor.id = ?1")
	Collection<Banner> findMyBanners(Integer sponsorId);

	@Query("select b.sponsor.id from Banner b where b.id = ?1")
	Integer getSponsorId(Integer bannerId);
}
