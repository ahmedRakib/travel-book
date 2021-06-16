package com.travelbook.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Un Authorized Access Exception Handler
 *
 * @since 1.0
 * @version 1.0
 * @author emon
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedAccess  extends RuntimeException  {
    public UnAuthorizedAccess(String s) {
        super(s);
    }
}
