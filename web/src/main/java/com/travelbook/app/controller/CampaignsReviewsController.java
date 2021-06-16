package com.travelbook.app.controller;

import com.travelbook.app.entity.Campaigns;
import com.travelbook.app.entity.CampaignsParticipants;
import com.travelbook.app.entity.CampaignsReviews;
import com.travelbook.app.exception.ClientException;
import com.travelbook.app.exception.EntityNotFound;
import com.travelbook.app.exception.ServerException;
import com.travelbook.app.exception.UnAuthorizedAccess;
import com.travelbook.app.service.CampaignsParticipantsService;
import com.travelbook.app.service.CampaignsReviewsService;
import com.travelbook.app.service.CampaignsService;
import com.travelbook.app.utils.CampaignsApprovalStatus;
import com.travelbook.app.utils.CampaignsStatus;
import com.travelbook.app.utils.Commons;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

/**
 * Campaigns Review Controller
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CampaignsReviewsController {
    private final CampaignsReviewsService campaignsReviewsService;
    private final CampaignsParticipantsService campaignsParticipantsService;
    private final CampaignsService campaignsService;

    /**
     * Add Reviews for Campaigns
     *
     * @param campaignsReviews valid Campaigns Reviews JSON
     * @return ResponseEntity<Map < String, Object>> - Return uri to find the campaigns reviews record from database
     * @throws EntityNotFound     If No Data Found
     * @throws ClientException    Exception From Client Data
     * @throws UnAuthorizedAccess If Unauthorized User want to access
     */
    @ApiOperation(value = "Add Reviews for Campaigns")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Reviews Added for Campaigns"),
                    @ApiResponse(code = 401, message = "Unauthorized to operate is operation"),
                    @ApiResponse(code = 404, message = "Campaign not found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @PostMapping("/campaigns-reviews")
    public ResponseEntity<Map<String, Object>> addReviewsForCampaign(@Valid @RequestBody CampaignsReviews campaignsReviews) {
        try {
            Campaigns campaigns = campaignsService.getCampaignsById(campaignsReviews.getCampaignsId());
            if (campaigns.getCampaignsStatusId() != CampaignsStatus.FINISHED.getId()) {
                throw new ClientException("Campaigns is not finished/not started/canceled");
            }
            if (campaigns.getCampaignsApprovalStatusId() != CampaignsApprovalStatus.APPROVED.getId()) {
                throw new ClientException("Campaigns request is not approved/ejected");
            }

            CampaignsParticipants campaignsParticipant = campaignsParticipantsService.getCampaignsParticipantsByCampaignsIdAndParticipantsId(campaignsReviews.getCampaignsId(), campaignsReviews.getUserId());
            if (campaignsParticipant == null) {
                throw new UnAuthorizedAccess("Not allowed to give reviews in this campaigns because you did not participate in this campaigns.");
            }

            CampaignsReviews reviews = campaignsReviewsService.save(campaignsReviews);
            URI url = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(reviews.getId())
                    .toUri();
            return ResponseEntity.created(url).build();
        } catch (EntityNotFound | ClientException | UnAuthorizedAccess exception) {
            throw new EntityNotFound(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Get Campaigns Reviews By Campaigns Id
     *
     * @param page Page Number Default 0
     * @param size Content Limit Default 3
     * @param id   Campaigns Id
     * @return ResponseEntity<Map < String, Object>> - return Campaigns Reviews By Campaigns Id
     * @throws EntityNotFound If No Data Found
     */
    @ApiOperation(value = "Get Campaigns Reviews By Campaigns Id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returned Data"),
                    @ApiResponse(code = 404, message = "Campaign not found"),
                    @ApiResponse(code = 406, message = "Wrong Input From Client"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @GetMapping("/campaigns-reviews/campaigns/{id}")
    public ResponseEntity<Map<String, Object>> getCampaignsReviewsByCampaignsId(@RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "3") int size,
                                                                                @PathVariable(value = "id") long id) {
        try {
            campaignsService.getCampaignsById(id);
            Page<CampaignsReviews> reviews = campaignsReviewsService.getCampaignsReviewsByCampaignsId(page, size, id);
            return Commons.getMapResponseEntityWithPagination(reviews);
        } catch (EntityNotFound exception) {
            throw new EntityNotFound(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Update the campaigns by id from database
     *
     * @param campaignsReviews valid Campaigns Reviews JSON
     * @param id               Campaigns Reviews Id
     * @return ResponseEntity<Map < String, Object>> - Return uri to find the campaigns record from database
     * @throws EntityNotFound If No Data Found
     */
    @ApiOperation(value = "Registered for Campaigns")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Registered for Campaigns"),
                    @ApiResponse(code = 404, message = "Campaign not found"),
                    @ApiResponse(code = 406, message = "Wrong Input From Client"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @PutMapping("/campaigns-reviews/{id}")
    public ResponseEntity<Map<String, Object>> updateCampaignsById(@Valid @RequestBody CampaignsReviews campaignsReviews,
                                                                   @PathVariable(value = "id") long id) {
        try {
            campaignsReviewsService.updateCampaignsReviewsById(campaignsReviews, id);
            return Commons.getMapResponseEntity("Resource updated");
        } catch (EntityNotFound exception) {
            throw new EntityNotFound(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Delete the campaigns review by id from database
     *
     * @param id Campaigns Reviews Id
     * @return ResponseEntity<Map < String, Object>> - Return record by id from database
     * @throws EntityNotFound     If No Data Found
     * @throws UnAuthorizedAccess If Unauthorized User want to access
     */
    @ApiOperation(value = "Delete Campaigns Reviews By Id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Delete Campaigns Reviews By Id"),
                    @ApiResponse(code = 404, message = "Reviews not found"),
                    @ApiResponse(code = 406, message = "Wrong Input From Client"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @DeleteMapping("/campaigns-reviews/reviews/{id}")
    public ResponseEntity<Map<String, Object>> deleteCampaignsReviewsById(@PathVariable(value = "id") long id) {
        try {
            CampaignsReviews reviews = campaignsReviewsService.findCampaignsReviewsById(id);
            if (reviews == null) {
                throw new EntityNotFound("Not reviews found.");
            } else {
                campaignsReviewsService.deleteReviewById(reviews.getId());
            }
            return Commons.getMapResponseEntity("Deletion Success");
        } catch (EntityNotFound | UnAuthorizedAccess ex) {
            throw new EntityNotFound(ex.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }
}
