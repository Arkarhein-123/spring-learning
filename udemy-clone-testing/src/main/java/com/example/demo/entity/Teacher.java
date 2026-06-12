package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("TEACHER")
public class Teacher extends User{
	
	
	private BigDecimal netWorth;
	private String educaion;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name= "teacher_skills")
	private List<String> skills =  new ArrayList<>();
	
	@OneToMany
	private List<Course> courses = new ArrayList<>();
	
	
	public Teacher(String username, String password, String email, BigDecimal netWorth, String educaion) {
		super(username, password, email);
		this.netWorth = netWorth;
		this.educaion = educaion;
	
	}

	public void addSkill(String skill) {
		this.skills.add(skill);
	}
	
	public void addCourse(Course course) {
		course.setTeacher(this);
		this.courses.add(course);
	}






}
