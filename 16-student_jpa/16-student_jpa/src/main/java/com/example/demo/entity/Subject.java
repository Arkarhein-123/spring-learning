package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Subject extends IdClass{
	
	private String subjectName;
	private int duration;
	private double fees;
	@OneToMany(mappedBy = "subject")
	private List<StudentSubject> studentSubjects= new ArrayList<>();
	
	public void addStudentSubjects(StudentSubject studentSubject) {
		studentSubject.setSubject(this);
		studentSubjects.add(studentSubject);
	}
	public Subject(String subjectName, int duration, double fees) {
		super();
		this.subjectName = subjectName;
		this.duration = duration;
		this.fees = fees;
	}

}
