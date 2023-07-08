package com.example.wordsearchgame.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GloblalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleMemberNotFoundException(CustomException customException){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customException.getMessage());
    }
}
