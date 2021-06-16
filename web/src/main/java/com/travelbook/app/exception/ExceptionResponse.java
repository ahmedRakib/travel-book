package com.travelbook.app.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Custom Exception Response
 *
 * @since 1.0
 * @version 1.0
 * @author emon
 */
@Data
@AllArgsConstructor
class ExceptionResponse {
    private Date timeStamp;
    private String message;
    private String details;
}
