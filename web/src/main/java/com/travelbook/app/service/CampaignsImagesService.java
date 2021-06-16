package com.travelbook.app.service;

import com.travelbook.app.entity.CampaignsImages;
import com.travelbook.app.exception.EntityNotFound;
import com.travelbook.app.repository.CampaignsImagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Campaigns Images Service
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CampaignsImagesService {
    private final CampaignsImagesRepository campaignsImagesRepository;

    public CampaignsImages getById(long id) {
        Optional<CampaignsImages> campaignsImages = campaignsImagesRepository.findById(id);
        if (!campaignsImages.isPresent()) {
            throw new EntityNotFound("Campaign Image not found");
        }

        return campaignsImages.get();
    }

    public List<CampaignsImages> getAllCampaignImagesByCampaignId(long campaignId) {
        return campaignsImagesRepository.findAllByCampaignsId(campaignId);
    }

    public void deleteCampaignsImageById(long id) {
        campaignsImagesRepository.deleteById(id);
    }
}
