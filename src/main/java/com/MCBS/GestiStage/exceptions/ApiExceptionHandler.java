package com.MCBS.GestiStage.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handelApiRequestException(ApiRequestException e )
    {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException= new ApiException(e.getMessage(),
               badRequest,
                new Date()
                );
        return new ResponseEntity<>(apiException ,badRequest);
    }
}
