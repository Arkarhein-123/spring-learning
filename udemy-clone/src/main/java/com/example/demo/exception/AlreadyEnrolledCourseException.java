package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AlreadyEnrolledCourseException extends ResponseStatusException {
    public AlreadyEnrolledCourseException(String studentName, long courseId){
        super(HttpStatus.BAD_REQUEST,"%s already enrolled with courseId: %s".formatted(studentName,courseId));
    }
}
