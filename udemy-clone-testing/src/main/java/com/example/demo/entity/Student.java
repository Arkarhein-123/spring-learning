package com.example.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("STUDENT")
public class Student extends User{
	
	private String address;
	@Enumerated(EnumType.STRING)
	private StudentEducation eduction;
	
	
	public Student(String username, String password, String email, String address, StudentEducation eduction) {
		super(username, password, email);
		this.address = address;
		this.eduction = eduction;
	}
	
	
	

	
}
