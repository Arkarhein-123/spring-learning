package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student  extends IdClass{
		private String name;
		private String School;
		
		@ManyToOne
		private Province province;
		
		@OneToMany(mappedBy = "student")
		private List<StudentSubject> studetnSubjects = new ArrayList<>();
		public Student(String name, String school) {
			super();
			this.name = name;
			School = school;
		}
		
		public void addStudentSubject(StudentSubject studentSubject) {
		    studentSubject.setStudent(this);
		    this.studetnSubjects.add(studentSubject);
		}
		
}
