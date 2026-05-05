package org.example.simplerestapi.exception;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ApiError message(Exception e){
        String errorMessage = Objects.nonNull(e.getMessage())  ? e.getMessage(): "unknown Error";
        String details = ExceptionUtils.getRootCauseMessage(e);
        return new ApiError(
                errorMessage,
                details,
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        return handleExceptionInternal(ex,
                message(ex),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleExceptionInternal(
                ex,
                message(ex),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );

    }

    @ExceptionHandler({DataIntegrityViolationException.class,FirstNameNotAdminException.class})
    public ResponseEntity<Object> globalExceptionHandler(Exception ex, WebRequest request){
        return handleExceptionInternal(
                ex,
                message(ex),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );

    }
}
