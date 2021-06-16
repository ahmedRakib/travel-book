package com.travelbook.app.repository;

import com.travelbook.app.entity.CampaignsReviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Cities Repository
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
public interface CampaignsReviewsRepository extends PagingAndSortingRepository<CampaignsReviews, Long> {
    /**
     * Find Campaigns Reviews By Campaigns Id
     *
     * @param paging Pagination
     * @param id Campaign id
     * @return Page<CampaignsReviews>: List of Campaign Review
     */
    Page<CampaignsReviews> findCampaignsReviewsByCampaignsId(Pageable paging, long id);

    /**
     * Find Campaigns Reviews By Id
     *
     * @param id Reviews id
     * @return Campaigns Reviews
     */
    CampaignsReviews findCampaignsReviewsById(long id);

    /**
     * Find Campaigns Reviews By Campaigns Id And User Id
     *
     * @param campaignsId Campaigns Id
     * @param userId User Id
     * @return Campaigns Reviews
     */
    CampaignsReviews findCampaignsReviewsByCampaignsIdAndUserId(long campaignsId, long userId);
}

