package com.travelbook.app.repository;

import com.travelbook.app.entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Countries Repository
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
public interface CountriesRepository extends JpaRepository<Countries, Long> {
}
