package com.travelbook.app.service;

import com.travelbook.app.entity.CampaignsReviews;
import com.travelbook.app.exception.EntityNotFound;
import com.travelbook.app.repository.CampaignsReviewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Campaigns Reviews Service
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CampaignsReviewsService {
    private final CampaignsReviewsRepository campaignsReviewsRepository;

    public CampaignsReviews save(CampaignsReviews campaignsReviews) {
        return campaignsReviewsRepository.save(campaignsReviews);
    }

    public Page<CampaignsReviews> getCampaignsReviewsByCampaignsId(int page, int size, long id) {
        Pageable paging = PageRequest.of(page, size);
        Page<CampaignsReviews> campaignsReviewsByCampaignsId = campaignsReviewsRepository.findCampaignsReviewsByCampaignsId(paging, id);
        if (campaignsReviewsByCampaignsId.getContent().size() == 0) {
            throw new EntityNotFound("No Data Found");
        }
        return campaignsReviewsByCampaignsId;
    }

    public CampaignsReviews getCampaignsReviewsById(long id) {
        return campaignsReviewsRepository.findCampaignsReviewsById(id);
    }

    public CampaignsReviews findCampaignsReviewsByCampaignsIdAndUserId(long campaignsId, long userId) {
        return campaignsReviewsRepository.findCampaignsReviewsByCampaignsIdAndUserId(campaignsId, userId);
    }

    public CampaignsReviews findCampaignsReviewsById(long id) {
        return campaignsReviewsRepository.findCampaignsReviewsById(id);
    }

    public void deleteReviewById(long id) {
        campaignsReviewsRepository.deleteById(id);
    }

    public CampaignsReviews updateCampaignsReviewsById(CampaignsReviews campaignsReviews, long id) {
        Optional<CampaignsReviews> campaignsReview = campaignsReviewsRepository.findById(id);
        if (campaignsReview.isPresent()) {
            campaignsReviews.setId(campaignsReview.get().getId());
            return campaignsReviewsRepository.save(campaignsReviews);
        } else {
            throw new EntityNotFound("Campaigns reviews not exists");
        }
    }
}
