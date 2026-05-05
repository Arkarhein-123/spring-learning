package org.example.simplerestapi.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiError(
        String message,
        String details,
        HttpStatus status,
        LocalDateTime timestamp
) {

}
