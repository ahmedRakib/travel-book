package com.travelbook.app.repository;

import com.travelbook.app.entity.CampaignsImages;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Campaigns Images Repository
 *
 * @author emon
 * @since 1.0
 * @version 1.0
 */
public interface CampaignsImagesRepository extends PagingAndSortingRepository<CampaignsImages, Long> {
    /**
     * Get All Campaigns Image by Campaigns Id.
     * If Not found Return empty.
     *
     * @param campaignId Must Required Campaigns Id
     * @return If campaigns by specific user
     */
    List<CampaignsImages> findAllByCampaignsId(long campaignId);

    /**
     * Delete Campaigns Image by ID
     *
     * @param id CampaignsStatus Id
     */
    @Modifying
    @Query("DELETE FROM CampaignsImages WHERE campaignsId = :id")
    void deleteByCampaignId(long id);
}
