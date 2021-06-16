package com.travelbook.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * Users Information
 *
 * @since 1.0
 * @version 1.0
 * @author emon
 */

@Getter
@Setter
@Data
@Entity
@AllArgsConstructor
@Table(name = "users")
public class Users implements Serializable {
    private static final long serialVersionUID = 645645631456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(min = 4, message = "Name has to be at least 4 character long")
    @NotEmpty(message = "Username cannot be empty")
    @NotNull(message = "Username cannot be null")
    @Column(name = "username")
    private String username;

    @Value("${users.email:a@gmail.com}")
    @NotEmpty(message = "Email cannot be empty")
    @NotNull(message = "Email cannot be null")
    @Column(name = "email")
    @Email(message = "Please provide valid email")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @NotNull(message = "Password cannot be null")
    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    @JsonIgnore
    private boolean enabled = true;

    @JsonIgnore
    @Column(name = "black_listed")
    private boolean blacklisted = false;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_authorities", joinColumns = @JoinColumn(name = "Users_id"), inverseJoinColumns = @JoinColumn(name = "authorities_id"))
    private Set<Authorities> authorities;

    protected Users(){}

    public long getId(){
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean getActive() {
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Authorities> geAuthorities() {
        return authorities;
    }
}
