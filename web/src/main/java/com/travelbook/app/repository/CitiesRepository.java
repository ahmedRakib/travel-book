package com.travelbook.app.repository;

import com.travelbook.app.entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Cities Repository
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
public interface CitiesRepository extends JpaRepository<Cities, Long> {
    /**
     * Find All Cities By State Id
     *
     * @param id  State Id
     * @return List<Cities>: List of Cities
     */
    List<Cities> findAllByStateId(long id);
}
