package com.travelbook.app.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Custom Basic Authentication Entry Point Class
 *
 * @author shakif
 * @version 1.0
 * @since 1.0
 */
@Service
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + authException.getMessage());
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("YOUR REALM");
        super.afterPropertiesSet();
    }
}
