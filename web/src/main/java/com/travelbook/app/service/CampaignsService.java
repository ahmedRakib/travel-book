package com.travelbook.app.service;

import com.travelbook.app.entity.Campaigns;
import com.travelbook.app.entity.CampaignsImages;
import com.travelbook.app.entity.Users;
import com.travelbook.app.exception.ClientException;
import com.travelbook.app.exception.EntityNotFound;
import com.travelbook.app.exception.ServerException;
import com.travelbook.app.repository.*;
import com.travelbook.app.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Campaigns Service
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CampaignsService {
    private final CampaignsRepository campaignsRepository;
    private final CampaignsImagesRepository campaignsImagesRepository;
    private final CampaignsParticipantsRepository campaignsParticipantsRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationFacade authenticationFacade;

    public Campaigns createCampaigns(Campaigns campaigns, List<MultipartFile> multipartFile) throws IOException {
        Campaigns save = updateCampaignsById(campaigns, campaigns.getId());
        if (save == null) {
            Users currentLoggedInUser = authenticationFacade.getLoggedInUser();
            campaigns.setUserId(currentLoggedInUser.getId());
            campaigns.setRemainingSeats(campaigns.getParticipantNumber()); // initially  remaining seats will be equal to participant number
            save = campaignsRepository.save(campaigns);
        }
        //Campaigns save = campaignsRepository.save(campaigns);
        String separator;
        if (System.getProperties().getProperty("os.name").startsWith("Windows")) {
            separator = "\\";
        } else {
            separator = "/";
        }
        for (MultipartFile value : multipartFile) {
            if ("".equals(value.getOriginalFilename())) {
                throw new ClientException("Atleast one image has to provide.");
            }
            String filePath = "src/main/resources" + separator + "images" + separator + System.currentTimeMillis() + "_" + Objects.requireNonNull(value.getOriginalFilename()).replaceAll(" ", "_");
            File file = new File(filePath);
            boolean newFile = file.createNewFile();
            if (!newFile) {
                throw new ServerException("Image file not created!!");
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(value.getBytes());
            }
            filePath = filePath.split("/resources")[1].replace("\\", "/");
            CampaignsImages campaignsImages = new CampaignsImages(-1, save.getId(), filePath);
            campaignsImagesRepository.save(campaignsImages);
        }
        return save;
    }

    public Page<Campaigns> getCampaigns(int page, int size, Integer campaignStatus, Integer campaignApprovalStatus) {
        Pageable paging = PageRequest.of(page, size);
        Page<Campaigns> campaigns;
        if (campaignApprovalStatus == -1 && campaignStatus == -1) {
            campaigns = campaignsRepository.findAll(paging);
        } else {
            campaigns = campaignsRepository
                    .findAllByCampaignsStatusIdAndCampaignsApprovalStatusIdAndStartTimeAfter(paging, campaignStatus, campaignApprovalStatus, new Date());
        }

        if (campaigns.getContent().size() == 0) {
            throw new EntityNotFound("No Data Found");
        }
        return campaigns;
    }

    public Page<Campaigns> getCampaigns(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Campaigns> campaigns = null;

        Users user = authenticationFacade.getLoggedInUser();

        campaigns = campaignsRepository.findAllByUserId(paging, user.getId());

        if (campaigns.getContent().size() == 0) {
            throw new EntityNotFound("No Data Found");
        }
        return campaigns;
    }

    public Campaigns getCampaignsById(long id) {
        Optional<Campaigns> campaign = campaignsRepository.findById(id);
        if (!campaign.isPresent()) {
            throw new EntityNotFound("Campaign not found");
        }
        return campaign.get();
    }

    public Page<Campaigns> getCampaignsByLocation(int page, int size, String location) {
        Pageable paging = PageRequest.of(page, size);
        Page<Campaigns> allByPlaceLike = campaignsRepository.findAllByPlaceContains(paging, location);
        if (allByPlaceLike.getContent().size() == 0) {
            throw new EntityNotFound("No Data Found");
        }
        return allByPlaceLike;
    }

    public Page<Campaigns> getCampaignsByBudgets(int page, int size, double minBudget, double maxBudget) {
        Pageable paging = PageRequest.of(page, size);
        Page<Campaigns> allByBudgetsIsBetween = campaignsRepository.findAllByBudgetsIsBetween(paging, minBudget, maxBudget);
        if (allByBudgetsIsBetween.getContent().size() == 0) {
            throw new EntityNotFound("No Data Found");
        }
        return allByBudgetsIsBetween;
    }

    public Page<Campaigns> getCampaignsByCampaignsStatusId(int page, int size, int id) {
        Pageable paging = PageRequest.of(page, size);
        Page<Campaigns> byCampaignsStatusId = campaignsRepository.findByCampaignsStatusId(paging, id);
        if (byCampaignsStatusId.getContent().size() == 0) {
            throw new EntityNotFound("No Data Found");
        }
        return byCampaignsStatusId;
    }

    public Page<Campaigns> getCampaignsByApprovalStatusId(int page, int size, int id) {
        Pageable paging = PageRequest.of(page, size);
        Page<Campaigns> byCampaignsApprovalStatusId = campaignsRepository.findByCampaignsApprovalStatusId(paging, id);
        if (byCampaignsApprovalStatusId.getContent().size() == 0) {
            throw new EntityNotFound("No Data Found");
        }
        return byCampaignsApprovalStatusId;
    }

    public Page<Campaigns> getCampaignsByUserId(int page, int size, long id) {
        Users user = userDetailsService.getUserById(id);
        if (user == null) {
            throw new EntityNotFound("User Not Found");
        }

        Pageable paging = PageRequest.of(page, size);
        Page<Campaigns> byUserId = campaignsRepository.findByUserId(paging, id);
        if (byUserId.getContent().size() == 0) {
            throw new EntityNotFound("No Data Found");
        }
        return byUserId;
    }

    public Campaigns updateCampaignsById(Campaigns campaigns, long id) {
        Optional<Campaigns> campaignDB = campaignsRepository.findById(id);


        if (campaignDB.isPresent()) {
            campaigns.setId(campaignDB.get().getId());

            int updatedNumberOfParticipantNumber = campaigns.getParticipantNumber() - campaignDB.get().getParticipantNumber();
            //updating the number of remaining seats
            campaigns.setRemainingSeats(campaignDB.get().getRemainingSeats() + updatedNumberOfParticipantNumber);

            return campaignsRepository.save(campaigns);
        }
        return null;
    }

    public void updateCampaignStatusId(int statusId, long id) {
        campaignsRepository.updateCampaignStatusId(statusId, id);
    }

    public void updateCampaignApprovalStatusId(int appApprovalStatusId, long id) {
        campaignsRepository.updateCampaignApprovalStatusId(appApprovalStatusId, id);
    }

    @Transactional
    public void deleteCampaignsById(long id) {
        campaignsParticipantsRepository.deleteByCampaignId(id);
        campaignsImagesRepository.deleteByCampaignId(id);
        campaignsRepository.deleteById(id);
    }

    public List<Object[]> getFilterCampaigns(Integer pageNumber, Integer pageSize, Integer countryId, Integer stateId, Integer cityId,
                                             String place, Integer minBudget, Integer maxBudget, Integer userId, Integer campaignsStatusId,
                                             Integer campaignsApprovalStatusId, String createdDateOrderBy) {
        List<Object[]> filterCampaigns = campaignsRepository.filterCampaigns(pageNumber, pageSize, countryId, stateId, cityId, place, minBudget, maxBudget, userId, campaignsStatusId, campaignsApprovalStatusId, createdDateOrderBy);
        if (filterCampaigns.size() == 0) {
            throw new EntityNotFound("No Data Found");
        }
        return filterCampaigns;
    }

    public int getFilterCampaignsCount(Integer countryId, Integer stateId, Integer cityId, String place, Integer minBudget, Integer maxBudget, Integer userId, Integer campaignsStatusId, Integer campaignsApprovalStatusId, String createdDateOrderBy) {
        int campaignsCount = campaignsRepository.filterCampaignsCount(countryId, stateId, cityId, place, minBudget, maxBudget, userId, campaignsStatusId, campaignsApprovalStatusId, createdDateOrderBy);
        if (campaignsCount == 0) {
            throw new EntityNotFound("No Data Found");
        }
        return campaignsCount;
    }
}
