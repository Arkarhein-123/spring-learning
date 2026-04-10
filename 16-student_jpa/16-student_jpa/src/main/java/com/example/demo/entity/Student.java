package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student extends IdClass {
	
	private String name;
	private String school;
	@ManyToOne
	private Province province;
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.PERSIST)
	private List<StudentSubject> studentSubjects = new ArrayList<>();
	
	public void addStudentSubject(StudentSubject studentSubject) {
		studentSubject.setStudent(this);
		studentSubjects.add(studentSubject); 
	}
	public Student(String name, String school) {
		super();
		this.name = name;
		this.school = school;
	}
	@Override
	public String toString() {
		return "Student [Name=" + name + ", School=" + school + "]";
	}
	
	

}
