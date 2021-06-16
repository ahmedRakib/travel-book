package com.travelbook.app.controller;

import com.travelbook.app.entity.Campaigns;
import com.travelbook.app.exception.*;
import com.travelbook.app.service.CampaignsImagesService;
import com.travelbook.app.service.CampaignsService;
import com.travelbook.app.utils.Commons;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

/**
 * Campaigns Repository
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CampaignsController {
    private final CampaignsService campaignsService;
    private final CampaignsImagesService campaignsImagesService;

    /**
     * Create new the campaign in database
     *
     * @param campaigns     Valid Campaigns JSON
     * @param multipartFile Campaigns Images
     * @return ResponseEntity<Campaigns> - Return uri to find the campaigns record from database
     * @throws ClientException Exception From Client Data
     * @throws ServerException Internal Server Error
     */
    @ApiOperation(value = "Create a Campaigns")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Campaigns Created"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @PostMapping(value = "/campaigns", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @Transactional
    public ResponseEntity<Map<String, Object>> createCampaigns(@RequestPart(value = "campaigns", required = true) Campaigns campaigns,
                                                               @RequestPart(value = "campaignsImagesList", required = true) List<MultipartFile> multipartFile) {
        try {
            campaignsService.createCampaigns(campaigns, multipartFile);
            return Commons.getMapResponseEntity("Resource updated");
        } catch (ClientException | ServerException exception) {
            throw new ServerException(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Get the campaigns from database
     *
     * @param page                      Page Number Default 0
     * @param size                      Content Limit Default 3
     * @param campaignsStatusId         {@link com.travelbook.app.utils.CampaignsStatus} Campaigns Status Id Default 1 -> DID_NOT_START
     * @param campaignsApprovalStatusId {@link com.travelbook.app.utils.CampaignsApprovalStatus} Campaigns Approval Status Id Default 2 -> APPROVED
     * @return ResponseEntity<Map < String, Object>> - Return All Listed Campaigns from database
     * @throws EntityNotFound If No Data Found
     */
    @ApiOperation(value = "Returns All Listed Campaigns")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returned Data"),
                    @ApiResponse(code = 404, message = "No Data Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @GetMapping("/campaigns")
    public ResponseEntity<Map<String, Object>> getCampaigns(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "3") int size,
                                                            @RequestParam(defaultValue = "1") int campaignsStatusId,
                                                            @RequestParam(defaultValue = "2") int campaignsApprovalStatusId) {
        try {
            Page<Campaigns> pageCampaigns = campaignsService.getCampaigns(page, size, campaignsStatusId, campaignsApprovalStatusId);
            return Commons.getMapResponseEntityWithPagination(pageCampaigns);
        } catch (EntityNotFound exception) {
            throw new EntityNotFound(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Get User Specific campaigns from database
     *
     * @param page     Page Number Default 0
     * @param size     Content Limit Default 3
     * @return ResponseEntity<Map < String, Object>> - Return All Listed Campaigns from database by user
     * @throws EntityNotFound If No Data Found
     */
    @ApiOperation(value = "Returns All Listed of User Campaigns")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returned Data"),
                    @ApiResponse(code = 404, message = "No Data Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @GetMapping("/user-campaigns")
    public ResponseEntity<Map<String, Object>> getCampaigns(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            Page<Campaigns> pageCampaigns = campaignsService.getCampaigns(page, size);
            return Commons.getMapResponseEntityWithPagination(pageCampaigns);
        } catch (EntityNotFound exception) {
            throw new EntityNotFound(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Get the campaign by id from database
     *
     * @return ResponseEntity<Map < String, Object>> - Return record by id from database
     * @throws EntityNotFound If No Data Found
     */
    @ApiOperation(value = "Get Specific Campaign by Id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returned Data"),
                    @ApiResponse(code = 404, message = "No Data Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @GetMapping("/campaigns/{id}")
    public ResponseEntity<Map<String, Object>> getCampaignsById(@PathVariable(value = "id") long id) {
        try {
            Campaigns campaigns = campaignsService.getCampaignsById(id);
            return Commons.getMapResponseEntity(campaigns);
        } catch (EntityNotFound entityNotFound) {
            throw new EntityNotFound(entityNotFound.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Filter Campaigns | All Fields are Optional
     *
     * @param pageNumber                Page number Default 1
     * @param pageSize                  Content Limit Default 3
     * @param countryId                 Country Id from Country Table
     * @param stateId                   State Id from State Table
     * @param cityId                    City Id from City Table
     * @param place                     Place of the Campaign
     * @param minBudget                 Minimum budget for the Campaign
     * @param maxBudget                 Maximum budget for the Campaign
     * @param userId                    Created Campaign by a Specific User
     * @param campaignsStatusId         {@link com.travelbook.app.utils.CampaignsStatus} Campaigns Status Id
     * @param campaignsApprovalStatusId {@link com.travelbook.app.utils.CampaignsApprovalStatus} Campaigns Approval Status Id
     * @param createdDateOrderBy        Campaigns Created Date Default DESC
     * @return ResponseEntity<Map < String, Object>> - Return record filtered from database
     * @throws EntityNotFound If No Data Found
     */
    @ApiOperation(value = "Filter Campaigns")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returned Data"),
                    @ApiResponse(code = 404, message = "No Data Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @GetMapping("/campaigns/filter")
    public ResponseEntity<Map<String, Object>> getFilterCampaigns(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize,
                                                                  @RequestParam(value = "countryId", required = false) Integer countryId,
                                                                  @RequestParam(value = "stateId", required = false) Integer stateId,
                                                                  @RequestParam(value = "cityId", required = false) Integer cityId,
                                                                  @RequestParam(value = "place", required = false) String place,
                                                                  @RequestParam(value = "minBudget", required = false) Integer minBudget,
                                                                  @RequestParam(value = "maxBudget", required = false) Integer maxBudget,
                                                                  @RequestParam(value = "userId", required = false) Integer userId,
                                                                  @RequestParam(value = "campaignsStatusId", required = false) Integer campaignsStatusId,
                                                                  @RequestParam(value = "campaignsApprovalStatusId", required = false) Integer campaignsApprovalStatusId,
                                                                  @RequestParam(value = "createdDateOrderBy", defaultValue = "DESC") String createdDateOrderBy) {
        try {
            List<Object[]> campaignList = campaignsService.getFilterCampaigns(pageNumber, pageSize, countryId, stateId, cityId, place, minBudget, maxBudget, userId, campaignsStatusId, campaignsApprovalStatusId, createdDateOrderBy);
            if (campaignList.size() == 0) {
                throw new EntityNotFound("No Data Found");
            }

            List<Map<String, Object>> campaigns = populateCampaignFilterData(campaignList);
            int items = campaignsService.getFilterCampaignsCount(countryId, stateId, cityId, place, minBudget, maxBudget, userId, campaignsStatusId, campaignsApprovalStatusId, createdDateOrderBy);
            return Commons.getMapResponseEntity(campaigns, items, pageNumber, pageSize);
        } catch (EntityNotFound entityNotFound) {
            throw new EntityNotFound(entityNotFound.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Update the campaigns by id from database
     *
     * @param campaigns Valid Campaigns JSON
     * @param id        Campaigns id
     * @return ResponseEntity<Map < String, Object>> - Return record of specific campaigns id from database
     * @throws EntityNotFound     If No Data Found
     * @throws ClientException    Exception From Client Data
     * @throws UnAuthorizedAccess If Unauthorized User want to access
     */
    @ApiOperation(value = "Update Campaigns by id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Data Updated"),
                    @ApiResponse(code = 401, message = "Unauthorized to operate is operation"),
                    @ApiResponse(code = 404, message = "No Data Found"),
                    @ApiResponse(code = 406, message = "Wrong Input From Client"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @PutMapping("/campaigns/{id}")
    public ResponseEntity<Map<String, Object>> updateCampaignsById(@Valid @RequestBody Campaigns campaigns,
                                                                   @PathVariable(value = "id") long id) {
        try {
            if (Commons.checkCampaignsStatusExistsById(campaigns.getCampaignsStatusId())) {
                Campaigns campaignsById = campaignsService.getCampaignsById(id);
                if (campaignsById != null) {
                    if (campaignsById.getUserId() == campaigns.getUserId()) {
                        campaignsService.createCampaigns(campaigns, new ArrayList<>());
                        return Commons.getMapResponseEntity("Resource updated");
                    } else {
                        throw new UnAuthorizedAccess("You are not authorized to update this campaigns.");
                    }
                } else {
                    throw new EntityNotFound("Campaigns id not exists");
                }
            } else {
                throw new ClientException("Invalid Campaign Status");
            }
        } catch (EntityNotFound | ClientException | UnAuthorizedAccess ex) {
            throw new EntityNotFound(ex.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Delete the campaigns by id from database
     *
     * @return ResponseEntity<Map < String, Object>> - HttpStatus.OK is when campaigns is deleted
     * @throws EntityNotFound     If No Data Found
     * @throws ClientException    Exception From Client Data
     * @throws UnAuthorizedAccess If Unauthorized User want to access
     */
    @ApiOperation(value = "Delete Campaigns by id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returned Data"),
                    @ApiResponse(code = 401, message = "Unauthorized to operate is operation"),
                    @ApiResponse(code = 404, message = "No Data Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @DeleteMapping("/campaigns/{id}")
    public ResponseEntity<Map<String, Object>> deleteCampaignsById(@PathVariable(value = "id") long id) {
        try {
            Campaigns campaigns = campaignsService.getCampaignsById(id);
            if (campaigns != null) {
                campaignsService.deleteCampaignsById(id);
                return Commons.getMapResponseEntity("Resource Deleted");
            } else {
                throw new EntityNotFound("Campaigns id not exists");
            }
        } catch (EntityNotFound | UnAuthorizedAccess ex) {
            throw new EntityNotFound(ex.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    public List<Map<String, Object>> populateCampaignFilterData(List<Object[]> campaignList) {
        List<Map<String, Object>> campaigns = new ArrayList<>();
        for (Object[] objects : campaignList) {
            Map<String, Object> campaign = new HashMap<>(11);
            campaign.put("id", objects[0]);
            campaign.put("title", objects[1]);
            campaign.put("place", objects[2]);
            campaign.put("startTime", objects[3]);
            campaign.put("duration", objects[4]);
            campaign.put("budgets", objects[5]);
            campaign.put("campaignsStatusId", objects[6]);
            campaign.put("userId", objects[7]);
            campaign.put("createdDate", objects[8]);
            campaign.put("description", objects[9]);
            campaign.put("campaignsApprovalStatusId", objects[10]);
            campaign.put("ratings", objects[11]);

            campaign.put("countryName", objects[12]);
            campaign.put("stateName", objects[13]);
            campaign.put("cityName", objects[14]);

            campaign.put("campaignsImagesList", campaignsImagesService.getAllCampaignImagesByCampaignId(Long.parseLong(String.valueOf(objects[0]))));
            campaigns.add(campaign);
        }
        return campaigns;
    }
}
