package com.bar.travelplanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UsernameAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<ErrorDetails> handleException(UsernameAlreadyExistsException exception) {

        return new ResponseEntity<>(
                new ErrorDetails(exception.getStatus().name(),
                        exception.getStatus().value(),
                        exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<ErrorDetails> handleException(EmailAlreadyExistsException exception) {

        return new ResponseEntity<>(
                new ErrorDetails(exception.getStatus().name(),
                        exception.getStatus().value(),
                        exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
