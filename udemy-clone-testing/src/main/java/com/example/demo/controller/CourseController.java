package com.example.demo.controller;

import com.example.demo.dao.CourseDao;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.CourseDto;
import com.example.demo.entity.Category;
import com.example.demo.service.CourseService;

import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {
	
	private final CourseDao courseDao;
	private final CourseService courseService;

	
	@PostMapping("/create-course")
	public ResponseEntity<String> createCourse(
			@RequestParam("title")String title,
			@RequestParam("fees")double fees,
			@RequestParam("description")String description,
			@RequestParam("category_name")String categoryName,
			@RequestParam(value="image",required = false)MultipartFile image,
			Principal principal
			) throws IOException{
		
		System.out.println("Principal" + principal.getName());
		String teacherName =  principal.getName();
		String returnString = courseService.createCourse(title, fees, description, 0, image, categoryName, teacherName);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(returnString);
	}
	
	@GetMapping("/find-all")
	public List<CourseDto> findAllCourse(){
		return courseService.findAllCourses();
	}

	@GetMapping("/teacher")
	public List<CourseDto> findAllCoursesByTeacher(Principal principal){
		return courseService.findAllCoursesByTeacherName(principal.getName());
	}

}
