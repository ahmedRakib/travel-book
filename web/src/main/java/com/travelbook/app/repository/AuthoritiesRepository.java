package com.travelbook.app.repository;

import com.travelbook.app.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User Authorities Repository
 *
 * @author emon
 * @since 1.0
 * @version 1.0
 */
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

}
