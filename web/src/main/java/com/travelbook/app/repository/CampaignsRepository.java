package com.travelbook.app.repository;

import com.travelbook.app.entity.Campaigns;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Campaigns Repository
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
public interface CampaignsRepository extends PagingAndSortingRepository<Campaigns, Long> {
    /**
     * Find By Campaigns Status Id
     *
     * @param pageable Pageable
     * @param id       CampaignsStatus Id
     * @return Page<Campaigns>: If campaigns of that specific status is found
     */
    Page<Campaigns> findByCampaignsStatusId(Pageable pageable, int id);

    /**
     * Find By Campaigns Approval Status Id
     *
     * @param pageable Pageable
     * @param id       CampaignsStatus Id
     * @return Page<Campaigns>: If campaigns of that approval status is found
     */
    Page<Campaigns> findByCampaignsApprovalStatusId(Pageable pageable, int id);

    /**
     * find Campaigns By User Id
     *
     * @param pageable Pageable
     * @param id       CampaignsStatus Id
     * @return Page<Campaigns>: If campaigns by specific user
     */
    Page<Campaigns> findByUserId(Pageable pageable, long id);

    /**
     * Find All By Place Contains
     *
     * @param pageable Pageable
     * @param id       CampaignsStatus Id
     * @return Page<Campaigns>: If campaigns by specific user
     */
    Page<Campaigns> findAllByPlaceContains(Pageable pageable, String id);

    /**
     * Find All Campaigns By Campaigns Status Id And Campaigns Approval Status Id
     *
     * @param pageable                  Pageable
     * @param CampaignsStatusId         Campaigns Status Id
     * @param CampaignsApprovalStatusId Campaigns Approval Status Id
     * @return Page<Campaigns>: If campaigns by specific user
     */
    Page<Campaigns> findAllByCampaignsStatusIdAndCampaignsApprovalStatusIdAndStartTimeAfter(Pageable pageable, Integer CampaignsStatusId, Integer CampaignsApprovalStatusId, Date startTime);

    /**
     * Find All By User Id
     *
     * @param pageable Pageable
     * @param userId   User Id
     * @return Optional<Campaigns>: If campaigns by specific user
     */
    Page<Campaigns> findAllByUserId(Pageable pageable, Long userId);

    /**
     * Find All By Budgets Is Between
     *
     * @param pageable  Pageable
     * @param minBudget Min Budget for Campaigns
     * @param maxBudget Max Budget for Campaigns
     * @return Page<Campaigns>: If campaigns by specific user
     */
    Page<Campaigns> findAllByBudgetsIsBetween(Pageable pageable, double minBudget, double maxBudget);

    /**
     * Update Campaign Status Id
     *
     * @param statusId Status Id
     * @param id       Campaigns Id
     */
    @Modifying
    @Query("UPDATE Campaigns SET campaignsStatusId = ?1 WHERE id = ?2")
    void updateCampaignStatusId(int statusId, long id);

    /**
     * Update Campaign Approval Status Id
     *
     * @param campaignsApprovalStatusId Campaigns Approval Status Id
     * @param id                        Campaigns Id
     */
    @Modifying
    @Query("UPDATE Campaigns SET campaignsApprovalStatusId = ?1 WHERE id = ?2")
    void updateCampaignApprovalStatusId(int campaignsApprovalStatusId, long id);

    /**
     * Filter Campaigns
     *
     * @param pageNumber                Page Number
     * @param pageSize                  Content Size
     * @param countryId                 Country Id
     * @param stateId                   State Id
     * @param cityId                    City Id
     * @param place                     Place of Campaign
     * @param minBudget                 Min Budget for Campaigns
     * @param maxBudget                 Max Budget for Campaigns
     * @param userId                    User Id
     * @param campaignsStatusId         Campaigns Status Id
     * @param campaignsApprovalStatusId Campaigns Approval Status Id
     * @param createdDateOrderBy        Created Date Order By
     * @return List<Object [ ]>: If campaigns by specific user
     */
    @Query(value = "CALL filterCampaigns(:pageNumber, :pageSize, :countryId, :stateId, :cityId, :place, :minBudget, :maxBudget, :userId, :campaignsStatusId, :campaignsApprovalStatusId, :createdDateOrderBy)", nativeQuery = true)
    List<Object[]> filterCampaigns(@Param("pageNumber") Integer pageNumber,
                                   @Param("pageSize") Integer pageSize,
                                   @Param("countryId") Integer countryId,
                                   @Param("stateId") Integer stateId,
                                   @Param("cityId") Integer cityId,
                                   @Param("place") String place,
                                   @Param("minBudget") Integer minBudget,
                                   @Param("maxBudget") Integer maxBudget,
                                   @Param("userId") Integer userId,
                                   @Param("campaignsStatusId") Integer campaignsStatusId,
                                   @Param("campaignsApprovalStatusId") Integer campaignsApprovalStatusId,
                                   @Param("createdDateOrderBy") String createdDateOrderBy);

    /**
     * Filter Campaigns Count
     *
     * @param countryId                 Country Id
     * @param stateId                   State Id
     * @param cityId                    City Id
     * @param place                     Place of Campaign
     * @param minBudget                 Min Budget for Campaigns
     * @param maxBudget                 Max Budget for Campaigns
     * @param userId                    User Id
     * @param campaignsStatusId         Campaigns Status Id
     * @param campaignsApprovalStatusId Campaigns Approval Status Id
     * @param createdDateOrderBy        Created Date Order By
     * @return int: Campaigns Total Number
     */
    @Query(value = "CALL filterCampaignsCount(:countryId, :stateId, :cityId, :place, :minBudget, :maxBudget, :userId, :campaignsStatusId, :campaignsApprovalStatusId, :createdDateOrderBy)", nativeQuery = true)
    int filterCampaignsCount(@Param("countryId") Integer countryId,
                             @Param("stateId") Integer stateId,
                             @Param("cityId") Integer cityId,
                             @Param("place") String place,
                             @Param("minBudget") Integer minBudget,
                             @Param("maxBudget") Integer maxBudget,
                             @Param("userId") Integer userId,
                             @Param("campaignsStatusId") Integer campaignsStatusId,
                             @Param("campaignsApprovalStatusId") Integer campaignsApprovalStatusId,
                             @Param("createdDateOrderBy") String createdDateOrderBy);
}
