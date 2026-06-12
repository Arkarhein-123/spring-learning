package com.example.demo.dto;

import java.util.List;

public record RegisterDto(
		String userType,
		String username,
		String password,
		String email,
		String address,
		String studentEducation,
		List<String> skills,
		String education
		) {

}
