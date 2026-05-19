package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OutOfStockException extends ResponseStatusException{
	public OutOfStockException() {
		super(HttpStatus.BAD_REQUEST,"Item is Out of Stock!");
	}

}
