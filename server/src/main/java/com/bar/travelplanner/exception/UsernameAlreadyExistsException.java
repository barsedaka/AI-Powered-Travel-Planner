package com.bar.travelplanner.exception;

import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistsException extends RegisterAbstractException {

    public UsernameAlreadyExistsException() {
        super("Username already exists!", HttpStatus.BAD_REQUEST);
    }
}
