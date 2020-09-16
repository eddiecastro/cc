package com.curry.sldemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    //Set up exception handler for PeopleListArgumentException for validation in PeopleController.
    //Advice is not required but cleans up the controller some
    @ExceptionHandler(PeopleListArgumentException.class)
    public ResponseEntity handlePeopleListArgumentException(PeopleListArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
