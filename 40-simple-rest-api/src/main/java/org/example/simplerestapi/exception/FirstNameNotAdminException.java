package org.example.simplerestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class FirstNameNotAdminException extends ResponseStatusException {

    public FirstNameNotAdminException( ) {
        super(HttpStatus.BAD_REQUEST);
    }
}
