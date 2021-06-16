package com.travelbook.app.service;

import com.travelbook.app.entity.Users;
import com.travelbook.app.repository.UsersRepository;
import com.travelbook.app.security.SecurityUser;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * User Details Service Implementations
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@Service
@Data
@RequiredArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Users users = usersRepository.findUserByUsername(username);
        SecurityUser userDetails;
        if (users != null) {
            userDetails = new SecurityUser(users);
        } else {
            throw new UsernameNotFoundException("User not exist with name : " + username);
        }
        return userDetails;
    }

    public Users getUserById(long id) {
        return usersRepository.findById(id);
    }

    public Users getUserByName(String userName) {
        return usersRepository.findUserByUsername(userName);
    }
}
