package com.travelbook.app.service;

import com.travelbook.app.entity.Campaigns;
import com.travelbook.app.entity.CampaignsParticipants;
import com.travelbook.app.entity.Users;
import com.travelbook.app.exception.EntityNotFound;
import com.travelbook.app.repository.CampaignsParticipantsRepository;
import com.travelbook.app.repository.CampaignsRepository;
import com.travelbook.app.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Campaigns Participants Service
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CampaignsParticipantsService {
    private final CampaignsParticipantsRepository campaignsParticipantsRepository;
    private final CampaignsRepository campaignsRepository;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    public CampaignsParticipants registerForCampaign(CampaignsParticipants campaignsParticipants) {
        Optional<Campaigns> campaigns = campaignsRepository.findById(campaignsParticipants.getCampaignsId());
        if (!campaigns.isPresent()) {
            throw new EntityNotFound("Campaign not found");
        }
        Users user = authenticationFacade.getLoggedInUser();
        if (user == null) {
            throw new EntityNotFound("User Not Found");
        }
        long campaignId = campaignsParticipants.getCampaignsId();
        long userId = user.getId();
        CampaignsParticipants campaignsParticipant = campaignsParticipantsRepository.findCampaignsParticipantsByCampaignsIdAndUserId(campaignId, userId);
        if (campaignsParticipant != null) {
            throw new EntityNotFound("User already has joined this campaign");
        }

        campaigns.get().setRemainingSeats(campaigns.get().getRemainingSeats() - 1);
        campaignsParticipants.setUserId(userId);
        campaignsRepository.save(campaigns.get());
        return campaignsParticipantsRepository.save(campaignsParticipants);
    }

    public Page<CampaignsParticipants> getCampaignsParticipantsByCampaignsId(int page, int size, long id) {
        Pageable paging = PageRequest.of(page, size);
        Page<CampaignsParticipants> participants = campaignsParticipantsRepository.findCampaignsParticipantsByCampaignsId(paging, id);
        if (participants.getContent().size() == 0) {
            throw new EntityNotFound("No Data Found");
        }
        return participants;
    }

    public CampaignsParticipants getCampaignsParticipantsByCampaignsIdAndParticipantsId(long campaignId, long participantsId) {
        return campaignsParticipantsRepository.findCampaignsParticipantsByCampaignsIdAndUserId(campaignId, participantsId);
    }

    public void deleteCampaignsParticipantsByCampaignsIdAAndUserId(long campaignsId, long userId){
        campaignsParticipantsRepository.deleteCampaignsParticipantsByCampaignsIdAndUserId(campaignsId, userId);
    }

    public void deleteCampaignsParticipantsById(long id) {
        campaignsParticipantsRepository.deleteById(id);
    }

    public CampaignsParticipants getCampaignsParticipantsById(long id) {
        Optional<CampaignsParticipants> campaignsParticipants = campaignsParticipantsRepository.findById(id);
        if (!campaignsParticipants.isPresent()) {
            throw new EntityNotFound("Campaign participants id not found");
        }
        return campaignsParticipants.get();
    }
}
