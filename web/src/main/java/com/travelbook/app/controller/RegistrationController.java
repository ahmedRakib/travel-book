package com.travelbook.app.controller;

import com.travelbook.app.entity.Users;
import com.travelbook.app.exception.ClientException;
import com.travelbook.app.exception.ServerException;
import com.travelbook.app.repository.UsersRepository;
import com.travelbook.app.security.AuthenticationFacade;
import com.travelbook.app.utils.Commons;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Registration Controller
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class RegistrationController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * SignUp End point
     *
     * @param users valid users JSON
     * @return ResponseEntity<HttpStatus> - HttpStatus.OK is when campaigns is deleted
     * @throws ClientException    Exception From Client Data
     */

    @PostMapping("/sign-up")
    public ResponseEntity<HttpStatus> signUp(@RequestBody Users users) {
        try {
            Users user = usersRepository.findUserByUsername(users.getUsername());
            if (user != null) {
                throw new ClientException("User name already exists with this username/mail");
            } else {
                String pwd = users.getPassword();
                String encryptPwd = passwordEncoder.encode(pwd);
                users.setPassword(encryptPwd);
                usersRepository.save(users);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        } catch (ClientException exception) {
            throw new ServerException(exception.getMessage());
        } catch (Exception exception) {
            throw new ServerException("Contact with vendor.");
        }
    }

    /**
     * SignIn End point
     *
     * @return ResponseEntity<HttpStatus> - HttpStatus.OK is when campaigns is deleted
     */

    @PostMapping("/sign-in")
    public ResponseEntity<Map<String, Object>> signIn() {
        Users user = authenticationFacade.getLoggedInUser();
        user.setPassword(null);
        return Commons.getMapResponseEntity(user);
    }
}
