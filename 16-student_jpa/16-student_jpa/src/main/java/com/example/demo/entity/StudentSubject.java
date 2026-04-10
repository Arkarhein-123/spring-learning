package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StudentSubject extends IdClass {

	private int marks;
	@ManyToOne
	private Student student;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Subject subject;

}
