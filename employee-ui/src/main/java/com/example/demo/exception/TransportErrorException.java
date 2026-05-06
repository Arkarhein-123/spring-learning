package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TransportErrorException extends ResponseStatusException {
	public TransportErrorException() {
		super(HttpStatus.INTERNAL_SERVER_ERROR,"can't connect to server");
	}
}
