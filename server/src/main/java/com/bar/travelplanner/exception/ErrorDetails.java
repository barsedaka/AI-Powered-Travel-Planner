package com.bar.travelplanner.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ErrorDetails {
    private final String error;
    private final int status;
    private final String message;
}
