package com.gmhapp.handler;

import com.gmhapp.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHendler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {ApiException.class})
    protected ResponseEntity<ErrorObject> handleConflict(
            ApiException ex, WebRequest request) {

        ErrorObject errorObject = ErrorObject.builder()
                .errorMessage(ex.getMessage())
                .errorCode(ex.getStatus().value())
                .path(request.getContextPath())
                //.path(request.getRequestURI())
                .build();
        return ResponseEntity.badRequest().body(errorObject);

    }
}