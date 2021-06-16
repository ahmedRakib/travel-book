package com.travelbook.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Entity Not Found
 *
 * @since 1.0
 * @version 1.0
 * @author emon
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFound extends RuntimeException  {
    public EntityNotFound(String s) {
        super(s);
    }
}
