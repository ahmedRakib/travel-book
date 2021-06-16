package com.travelbook.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Any wrong input from the user
 *
 * @author emon
 * @version 1.0
 * @since 1.0
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ClientException extends RuntimeException  {
    public ClientException(String s) {
        super(s);
    }
}
