package com.piml.gandalf.gandalf.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice(annotations = RestController.class)
public class MyExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<?> handleUserException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
    @ExceptionHandler(SignInNotAuthorizedException.class)
    protected  ResponseEntity<?> unauthorizedUser(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
    }
}