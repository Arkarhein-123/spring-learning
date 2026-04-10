package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Province extends IdClass {
		private String provinceName;
		
		@OneToMany(mappedBy = "province",
				cascade = CascadeType.PERSIST)
		
		private List<Student> students = new ArrayList<Student>();
		
		public void addStudent(Student student) {
			student.setProvince(this);
			students.add(student);
		}

}
