package com.piml.gandalf.gandalf.exception.handler;


public class SignInNotAuthorizedException extends RuntimeException {

    public SignInNotAuthorizedException(String message) {
        super(message);
    }
}
