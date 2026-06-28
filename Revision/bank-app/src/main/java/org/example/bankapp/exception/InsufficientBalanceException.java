package org.example.bankapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InsufficientBalanceException extends ResponseStatusException {
    public InsufficientBalanceException(String message){
        super(HttpStatus.UNPROCESSABLE_ENTITY,message);
    }
}
