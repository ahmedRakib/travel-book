package com.travelbook.app.repository;

import com.travelbook.app.entity.HostRating;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Host Rating Repository
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
public interface HostRatingRepository extends PagingAndSortingRepository<HostRating, Long> {
}
