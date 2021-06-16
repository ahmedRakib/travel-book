package com.travelbook.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Duplicate User Found
 *
 * @since 1.0
 * @version 1.0
 * @author emon
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class DuplicateUserFound extends RuntimeException  {
    public DuplicateUserFound(String s) {
        super(s);
    }
}
