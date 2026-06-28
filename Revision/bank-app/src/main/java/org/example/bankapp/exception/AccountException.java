package org.example.bankapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class AccountException extends ResponseStatusException {
    public AccountException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
