package com.bar.travelplanner.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends RegisterAbstractException {

    public EmailAlreadyExistsException() {
        super("Email already exists!", HttpStatus.BAD_REQUEST);
    }
}
