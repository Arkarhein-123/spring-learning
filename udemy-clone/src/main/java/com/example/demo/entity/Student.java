package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("STUDENT")
public class Student extends User{
	
	private String address;
	@OneToMany(mappedBy = "student")
	private List<StudentEnrollCourse> enrolledCourses = new ArrayList<>();

	public void addEnrolledCourse(StudentEnrollCourse course){
		course.setStudent(this);
		enrolledCourses.add(course);
	}
	
	public Student(
			String username,
			String password, 
			String email, 
			String address, 
			StudentEducation studentEducation) {
		super(username, password, email);
		this.address = address;
		this.studentEducation = studentEducation;
	}


	@Enumerated(EnumType.STRING)
	private StudentEducation studentEducation;

}






