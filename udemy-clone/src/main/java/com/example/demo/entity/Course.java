package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course extends IdClass{
	private String title;
	private BigDecimal fees;
	@Column(columnDefinition = "text")
	private String description;
	private int studetCount;
	@Lob
	private byte[] image;
	@ManyToOne
	private Category category;
	@ManyToOne
	private Teacher teacher;
	@CollectionTable(name = "course_lessons")
	@ElementCollection(fetch = FetchType.EAGER)
	private List<CourseLesson> courseLessons = new ArrayList<>();

	@OneToMany(mappedBy = "course")
	private List<StudentEnrollCourse> enrolledCourse = new ArrayList<>();

	public void addCourseLesson(CourseLesson courseLesson){
		courseLessons.add(courseLesson);
	}

	public void addEnrolledCourse(StudentEnrollCourse course) {
		course.setCourse(this);
		enrolledCourse.add(course);
	}
}







