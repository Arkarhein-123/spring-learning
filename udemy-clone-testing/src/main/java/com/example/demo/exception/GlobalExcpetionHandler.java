package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExcpetionHandler extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(value = {
		
		TeacherNotFoundException.class,
		DataIntegrityViolationException.class
	})
	public ResponseEntity<Object> globalExceptionMethod(Exception e,WebRequest request) 
			throws Exception{
		
		Map<String, String> errorMap = Map.of(
				"errorMessage", e.getMessage(),
				"ErrorTime",LocalDateTime.now().toString(),
				"ErrorStatus",HttpStatus.BAD_REQUEST.toString()
		);
		
		return handleExceptionInternal(
				e, 
				errorMap,
				new HttpHeaders(), 
				HttpStatus.BAD_REQUEST,
				request);
		
	}

	
	
}
