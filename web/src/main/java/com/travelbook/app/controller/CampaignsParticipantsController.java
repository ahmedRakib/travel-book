package com.travelbook.app.controller;

import com.travelbook.app.entity.Campaigns;
import com.travelbook.app.entity.CampaignsParticipants;
import com.travelbook.app.exception.ClientException;
import com.travelbook.app.exception.EntityNotFound;
import com.travelbook.app.exception.ServerException;
import com.travelbook.app.exception.UnAuthorizedAccess;
import com.travelbook.app.service.CampaignsParticipantsService;
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
 * Campaigns Participants Controller
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CampaignsParticipantsController {
    private final CampaignsParticipantsService campaignsParticipantsService;
    private final CampaignsService campaignsService;

    /**
     * Registered for Campaigns
     *
     * @param campaignsParticipants valid Campaigns Participants JSON
     * @throws EntityNotFound  If No Data Found
     * @throws ClientException Exception From Client Data
     * @return ResponseEntity<Map < String, Object>> - Return uri of Campaigns Participants from database
     */
    @ApiOperation(value = "Registered for Campaigns")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Registered for Campaigns"),
                    @ApiResponse(code = 404, message = "Entity Not Found"),
                    @ApiResponse(code = 406, message = "Wrong Input From Client"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @PostMapping("/campaigns-participants")
    public ResponseEntity<Map<String, Object>> registerForCampaign(@Valid @RequestBody CampaignsParticipants campaignsParticipants) {
        try {
            Campaigns campaignsById = campaignsService.getCampaignsById(campaignsParticipants.getCampaignsId());
            if (campaignsById.getCampaignsStatusId() != CampaignsStatus.DID_NOT_START.getId()) {
                throw new ClientException("Not allowed to join this campaigns because it is running/finished/canceled.");
            }

            if (campaignsById.getCampaignsApprovalStatusId() != CampaignsApprovalStatus.APPROVED.getId()) {
                throw new ClientException("Not allowed to join this campaigns is not approved/rejected.");
            }

            if (campaignsById.getRemainingSeats() == 0) {
                throw new ClientException("Cannot Join!! The maximum capacity of participant number is already reached");
            }

            CampaignsParticipants campaignsParticipant = campaignsParticipantsService.registerForCampaign(campaignsParticipants);
            URI url = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(campaignsParticipant.getId())
                    .toUri();
            return ResponseEntity.created(url).build();
        } catch (EntityNotFound | ClientException exception) {
            throw new EntityNotFound(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Get all partitions of a campaigns by id from database
     *
     * @param page Page number Default 1
     * @param size Content Limit Default 3
     * @param id   Campaigns Id
     * @return ResponseEntity<Map < String, Object>> - Return record by id from database
     * @throws EntityNotFound If No Data Found
     */
    @ApiOperation(value = "Get all participants of a campaigns by id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Registered for Campaigns"),
                    @ApiResponse(code = 404, message = "Campaigns not found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @GetMapping("/campaigns-participants/campaigns/{id}")
    public ResponseEntity<Map<String, Object>> getCampaignsParticipantsByCampaignsId(@RequestParam(defaultValue = "0") int page,
                                                                                     @RequestParam(defaultValue = "3") int size,
                                                                                     @PathVariable(value = "id") long id) {
        try {
            campaignsService.getCampaignsById(id);
            Page<CampaignsParticipants> participantsByCampaignsId = campaignsParticipantsService.getCampaignsParticipantsByCampaignsId(page, size, id);
            return Commons.getMapResponseEntityWithPagination(participantsByCampaignsId);
        } catch (EntityNotFound exception) {
            throw new EntityNotFound(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * Delete the campaigns participant by id from database
     *
     * @param id   Campaigns Participant Id
     * @return ResponseEntity<Map < String, Object>> - Return record by id from database
     */
    @ApiOperation(value = "De register from a campaigns by campaigns and user id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Registered for Campaigns"),
                    @ApiResponse(code = 404, message = "Campaigns not found"),
                    @ApiResponse(code = 406, message = "Wrong Input From Client"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            }
    )
    @DeleteMapping("/campaigns-participants/{id}")
    public ResponseEntity<Map<String, Object>> deleteCampaignsParticipantsByCampaignsId(@PathVariable(value = "id") long id) {
        try {
            campaignsParticipantsService.getCampaignsParticipantsById(id);
            campaignsParticipantsService.deleteCampaignsParticipantsById(id);
            return Commons.getMapResponseEntity("Removed from Campaigns");
        } catch (EntityNotFound | UnAuthorizedAccess exception) {
            throw new EntityNotFound(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }
}
