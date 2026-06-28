package org.example.bankapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorResponse> handleAccountException(
            AccountException exception, WebRequest request
    ){
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                exception.getReason(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, exception.getStatusCode());
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(
            InsufficientBalanceException exception, WebRequest request
    ){
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                exception.getReason(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response,exception.getStatusCode());
    }
}
