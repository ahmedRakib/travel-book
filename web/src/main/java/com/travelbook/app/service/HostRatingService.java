package com.travelbook.app.service;

import com.travelbook.app.entity.HostRating;
import com.travelbook.app.exception.EntityNotFound;
import com.travelbook.app.repository.HostRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Host Service
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class HostRatingService {
    private final HostRatingRepository hostRatingRepository;

    public HostRating save(HostRating hostRating) {
        return hostRatingRepository.save(hostRating);
    }

    public void updateHostRatingById(HostRating hostRa, long id) {
        Optional<HostRating> hostRating = hostRatingRepository.findById(id);
        if (hostRating.isPresent()) {
            hostRa.setId(hostRating.get().getId());
            hostRatingRepository.save(hostRa);
        } else {
            throw new EntityNotFound("Data not found");
        }
    }

    public void deleteHostRatingById(long id) {
        hostRatingRepository.deleteById(id);
    }
}
