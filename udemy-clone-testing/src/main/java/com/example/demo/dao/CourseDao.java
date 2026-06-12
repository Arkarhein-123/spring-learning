package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.CourseDto;
import com.example.demo.entity.Course;
import org.springframework.data.repository.query.Param;

public interface CourseDao extends JpaRepository<Course, Long>{
	
	
//	private Long courseId;
//	   private String description;
//	   private BigDecimal fees;
//	   private String category;
//	   private String teacherName;
//	   private String imagebase64;


	@Query("""
			select new com.example.demo.dto.CourseDto(
			c.id,
			c.title,
			c.fees,
			c.description,
			cat.categoryName,
			t.username,
			c.image
			
			)from Course as c 
			join c.category as cat 
			join c.teacher as t 
			""")
	List<CourseDto> findAllCourses();
	
	@Query("""
			select new com.example.demo.dto.CourseDto(
			c.id,
			c.title,
			c.fees,
			c.description,
			cat.categoryName,
			t.username,
			c.image
			
			)from Course as c 
			join c.category as cat 
			join c.teacher as t 
			where t.username = :username
			""")
	List<CourseDto> findAllCoursesByTeacherName(@Param("username")String username);

}
