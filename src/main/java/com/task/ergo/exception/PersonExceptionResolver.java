package com.task.ergo.exception;

import com.task.ergo.controller.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.Logger;


@ControllerAdvice
public class PersonExceptionResolver extends ResponseEntityExceptionHandler {

    Logger logger = Logger.getLogger(PersonExceptionResolver.class.getName());

    @ExceptionHandler(PersonException.class)
    public ResponseEntity<Object> handlePersonException(PersonException ex, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(ex.getMessage());
        errorResponse.setPath(request.getDescription(false).substring(4));

        ex.printStackTrace();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(PersonException ex, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setError(ex.getMessage());
        errorResponse.setPath(request.getDescription(false).substring(4));

        ex.printStackTrace();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
