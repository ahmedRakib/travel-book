package com.travelbook.app.repository;

import com.travelbook.app.entity.CampaignsParticipants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Campaigns Participants Repository
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
public interface CampaignsParticipantsRepository extends PagingAndSortingRepository<CampaignsParticipants, Long> {
    /**
     * Find Campaigns Participants By Campaigns Id
     *
     * @param pageable Pagination
     * @param id       Must Required Campaign Id
     * @return Campaigns Participants By Campaigns Id
     */
    Page<CampaignsParticipants> findCampaignsParticipantsByCampaignsId(Pageable pageable, long id);

    /**
     * Find Campaigns Participants By Campaigns Id And User Id
     *
     * @param campaignId     Must Required Campaign Id
     * @param participantsId Must Required Participants Id
     * @return Campaigns Participants By Campaigns Id And User Id
     */
    CampaignsParticipants findCampaignsParticipantsByCampaignsIdAndUserId(long campaignId, long participantsId);

    /**
     * Delete Campaigns Participants By Campaigns Id And User Id
     *
     * @param campaignId Must Required Campaign Id
     * @param userId     Must Required User Id
     */
    void deleteCampaignsParticipantsByCampaignsIdAndUserId(long campaignId, long userId);

    /**
     * Delete Campaigns By Campaigns Id
     *
     * @param id Campaign Id
     */
    @Modifying
    @Query("delete from CampaignsParticipants where campaignsId = :id")
    void deleteByCampaignId(long id);
}
