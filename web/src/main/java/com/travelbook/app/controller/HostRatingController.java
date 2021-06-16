package com.travelbook.app.controller;

import com.travelbook.app.entity.Campaigns;
import com.travelbook.app.entity.CampaignsParticipants;
import com.travelbook.app.entity.HostRating;
import com.travelbook.app.exception.ClientException;
import com.travelbook.app.exception.EntityNotFound;
import com.travelbook.app.exception.ServerException;
import com.travelbook.app.exception.UnAuthorizedAccess;
import com.travelbook.app.service.CampaignsParticipantsService;
import com.travelbook.app.service.CampaignsService;
import com.travelbook.app.service.HostRatingService;
import com.travelbook.app.utils.CampaignsApprovalStatus;
import com.travelbook.app.utils.CampaignsStatus;
import com.travelbook.app.utils.Commons;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

/**
 * Host Rating Controller
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HostRatingController {
    private final HostRatingService hostRatingService;
    private final CampaignsService campaignsService;
    private final CampaignsParticipantsService campaignsParticipantsService;

    /**
     * Rate a host
     *
     * @param hostRating Valid HostRating JSON
     * @return ResponseEntity<Campaigns> - Return uri of the new created host rating
     * @throws EntityNotFound     If No Data Found
     * @throws ClientException    Exception From Client Data
     * @throws UnAuthorizedAccess If Unauthorized User want to access
     */
    @ApiOperation(value = "Rate a host")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Rate a host"),
                    @ApiResponse(code = 401, message = "Un Authorized"),
                    @ApiResponse(code = 404, message = "Data not found"),
                    @ApiResponse(code = 406, message = "Wrong Input From Client"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @PostMapping("/host-rating")
    @Transactional
    public ResponseEntity<Map<String, Object>> rateHost(@Valid @RequestBody HostRating hostRating) {
        try {
            Campaigns campaignsById = campaignsService.getCampaignsById(hostRating.getCampaignsId());
            if (campaignsById.getCampaignsStatusId() != CampaignsStatus.FINISHED.getId()) {
                throw new ClientException("Campaigns is not finished yet/not started/canceled");
            }
            if (campaignsById.getCampaignsApprovalStatusId() != CampaignsApprovalStatus.APPROVED.getId()) {
                throw new ClientException("Campaigns request is not approved/rejected");
            }

            CampaignsParticipants campaignsParticipant = campaignsParticipantsService.getCampaignsParticipantsByCampaignsIdAndParticipantsId(hostRating.getCampaignsId(), hostRating.getUserId());
            if (campaignsParticipant == null) {
                throw new UnAuthorizedAccess("Not allowed to give reviews in this campaigns because you did not participate in this campaigns.");
            }

            HostRating rating = hostRatingService.save(hostRating);
            URI url = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(rating.getId())
                    .toUri();

            return ResponseEntity.created(url).build();
        } catch (EntityNotFound | ClientException | UnAuthorizedAccess ex) {
            throw new EntityNotFound(ex.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Update the rate of host by id
     *
     * @param hostRating Valid HostRating JSON
     * @return ResponseEntity<Campaigns> - Return uri of the new updated host rating
     * @throws EntityNotFound  If No Data Found
     * @throws ClientException Exception From Client Data
     */
    @ApiOperation(value = "Update Rate of a host")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Updated rating of a host"),
                    @ApiResponse(code = 404, message = "Campaigns not found"),
                    @ApiResponse(code = 406, message = "Wrong Input From Client"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @PutMapping("/host-rating/{id}")
    public ResponseEntity<Map<String, Object>> updateHostRatingById(@Valid @RequestBody HostRating hostRating,
                                                                    @PathVariable(value = "id") long id) {
        try {
            CampaignsParticipants campaignsParticipant = campaignsParticipantsService.getCampaignsParticipantsByCampaignsIdAndParticipantsId(hostRating.getCampaignsId(), hostRating.getUserId());
            if (campaignsParticipant == null) {
                throw new ClientException("Not allowed to give reviews in this campaigns because you did not participate in this campaigns.");
            }

            hostRatingService.updateHostRatingById(hostRating, id);
            return Commons.getMapResponseEntity("Resource updated");
        } catch (EntityNotFound | ClientException ex) {
            throw new EntityNotFound(ex.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Get the campaigns by id from database
     *
     * @param campaignsId Campaigns Id
     * @param userId      User Id
     * @param id          User Rating Id
     * @return ResponseEntity<Campaigns> - Return uri of the new created host rating
     * @throws EntityNotFound     If No Data Found
     * @throws ClientException    Exception From Client Data
     */
    @ApiOperation(value = "Delete Rate of a host")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Resource Deleted"),
                    @ApiResponse(code = 404, message = "Campaigns not found"),
                    @ApiResponse(code = 406, message = "Wrong Input From Client"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @DeleteMapping("/host-rating/{id}")
    public ResponseEntity<Map<String, Object>> deleteHostRatingById(@RequestParam(defaultValue = "0") long campaignsId,
                                                                    @RequestParam(defaultValue = "3") long userId,
                                                                    @PathVariable(value = "id") long id) {
        try {
            CampaignsParticipants campaignsParticipant = campaignsParticipantsService.getCampaignsParticipantsByCampaignsIdAndParticipantsId(campaignsId, userId);
            if (campaignsParticipant == null) {
                throw new ClientException("Not allowed to give reviews in this campaigns because you did not participate in this campaigns.");
            }

            hostRatingService.deleteHostRatingById(id);
            return Commons.getMapResponseEntity("Deletion Success");
        } catch (EntityNotFound | ClientException ex) {
            throw new EntityNotFound(ex.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }
}
