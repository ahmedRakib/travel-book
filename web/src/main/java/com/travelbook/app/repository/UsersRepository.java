package com.travelbook.app.repository;

import com.travelbook.app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Users Repository
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
    /**
     * Find User By Username
     *
     * @param username User username
     * @return Optional<Users>: If user is found
     */

    Users findUserByUsername(String username);

    /**
     * Find User By Id
     *
     * @param id User id
     * @return Optional<Users>: If user is found
     */
    Users findById(long id);
}
