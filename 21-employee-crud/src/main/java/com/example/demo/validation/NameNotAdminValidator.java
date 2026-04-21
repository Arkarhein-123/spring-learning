package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameNotAdminValidator implements ConstraintValidator<NamedNotAdmin, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !value.trim().equalsIgnoreCase("admin");
	}
	
}
