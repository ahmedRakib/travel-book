package com.travelbook.app.exception;

import com.travelbook.app.entity.Users;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of custom exception
 *
 * @since 1.0
 * @version 1.0
 * @author emon
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFound.class)
    public final ResponseEntity<Users> handleNotFoundUsersException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateUserFound.class)
    public final ResponseEntity<Users> handleDuplicateUsersException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ServerException.class)
    public final ResponseEntity<Users> handleServerException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ClientException.class)
    public final ResponseEntity<Users> handleClientException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(GeneralException.class)
    public final ResponseEntity<Users> handleGeneralException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnAuthorizedAccess.class)
    public final ResponseEntity<Users> handleUnAuthorizedAccess(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                ex.getBindingResult()
                        .getAllErrors()
                        .get(0)
                        .getDefaultMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    Date convertToDate(String receivedDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return formatter.parse(receivedDate);
    }
}
