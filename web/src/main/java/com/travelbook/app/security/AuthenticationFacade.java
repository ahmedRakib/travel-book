package com.travelbook.app.security;

import com.travelbook.app.entity.Users;
import com.travelbook.app.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author shakif
 *
 * @since 1.0
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class AuthenticationFacade {
    private final UsersRepository usersRepository;

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Users getLoggedInUser() {
        return usersRepository.findUserByUsername(getAuthentication().getName());
    }
}
