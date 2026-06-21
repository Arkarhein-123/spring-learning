package com.example.demo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import com.example.demo.entity.CourseLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.CourseDto;
import com.example.demo.entity.Category;
import com.example.demo.service.CourseService;

import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
	@Autowired
	private CourseService courseService;

	public record EnrolledDto(List<Long> courseIds){}

	@GetMapping("/{id}/course-lessons")
	public List<CourseLesson> fetchAllCourseLessons(@PathVariable("id")Long courseId){
		return courseService.findAllLessonsByCourseId(courseId);
	}

	@PostMapping("/{id}/lessons")
	public ResponseEntity<String> addlessons(@RequestBody CourseLesson courseLesson,
											 @PathVariable("id") Long courseId){
		String returnString = courseService.addLessons(courseLesson,courseId);
		return ResponseEntity.ok().body(returnString);
	}

	@GetMapping("/enrolled-courses")
	public List<CourseDto> fetchAllEnrolledCourses(Principal principal){
		return courseService.getAllEnrolledCourses(principal.getName());
	}

	@PostMapping("/enroll")
	public ResponseEntity<String> enrolledCourse(@RequestBody EnrolledDto enrolledDto, Principal principal){
		System.out.println("Payload received: " + enrolledDto.courseIds);
		String retrunString = courseService.enrolledCourse( enrolledDto.courseIds, principal.getName());
		return new ResponseEntity<String>(retrunString,HttpStatus.CREATED);
	}

	@GetMapping
	public List<CourseDto> findAllCourses(){
		return courseService.findAllCourses();
	}
	@GetMapping("/teacher")
	public List<CourseDto> findAllCoursesByTeacher(Principal principal){	
		return courseService
				.findAllCoursesByTeacherName(principal.getName());
	}
	@PostMapping
	public ResponseEntity<String> createCourse(
			@RequestParam("title")String title,
			@RequestParam("description")String description,
			@RequestParam("fees")Double fees,
			@RequestParam("category_name")String categoryName,
			@RequestParam(value="image",required = false)MultipartFile image,
			Principal principal) 
	throws IOException{
		String teacherName=principal.getName();
		String returnString=courseService.createCourse(title, description, fees, image, categoryName, teacherName);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(returnString);
	}



}
