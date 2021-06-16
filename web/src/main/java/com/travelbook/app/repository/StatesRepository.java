package com.travelbook.app.repository;

import com.travelbook.app.entity.States;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * States Repository
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
public interface StatesRepository extends JpaRepository<States, Long> {
    /**
     * Find All States By Country Id
     *
     * @param id  country id
     * @return List<States>: List of States
     */
    List<States> findAllByCountryId(long id);
}
