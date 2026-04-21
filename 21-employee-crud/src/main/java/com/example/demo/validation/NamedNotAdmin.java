package com.example.demo.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = NameNotAdminValidator.class)
public @interface NamedNotAdmin {
	String message() default "Name cannot be 'Admin'";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
