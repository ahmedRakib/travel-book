package com.travelbook.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * General Exception Handler
 *
 * @since 1.0
 * @version 1.0
 * @author emon
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class GeneralException extends RuntimeException  {
    @Deprecated
    public GeneralException(String s) {
        super(s);
    }
}
