package com.travelbook.app.security;

import com.travelbook.app.entity.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * user Security Implementation
 *
 * @since 1.0
 * @version 1.0
 * @author emon
 */
@Getter
@Setter
@RequiredArgsConstructor
public class SecurityUser implements UserDetails {
    private static final long serialVersionUID = 1256711395932122675L;
    @Autowired
    private Users users;

    public SecurityUser(Users user) {
        this.users = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.users.geAuthorities().stream().map(authority -> new SimpleGrantedAuthority("ROLE_" + authority))
                .collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return this.users.getPassword();
    }

    @Override
    public String getUsername() {
        return this.users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.users.getActive();
    }
}
