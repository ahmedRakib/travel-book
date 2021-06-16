package com.travelbook.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Internal Server Exception
 *
 * @since 1.0
 * @version 1.0
 * @author emon
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerException extends RuntimeException  {
    public ServerException(String s) {
        super(s);
    }
}
