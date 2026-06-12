package com.example.demo.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.CourseDao;
import com.example.demo.dao.TeacherDao;
import com.example.demo.dto.CourseDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Course;
import com.example.demo.entity.Teacher;
import com.example.demo.exception.TeacherNotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
	private final CourseDao courseDao;
	private final CategoryDao categoryDao;
	private final TeacherDao teacherDao;
	
	public List<CourseDto> findAllCourses(){
		return courseDao.findAllCourses();
	}
	public List<CourseDto> findAllCoursesByTeacherName(String username){
		return courseDao.findAllCoursesByTeacherName(username);
	}
	
	@Transactional
	public String createCourse(
			String title,
			String description,
			double fees,
			MultipartFile image,
			String categoryName,
			String teacherName
			)throws IOException{
		Category category=getCategoryByName(categoryName);
		Teacher teacher=getTeacherByName(teacherName);
		Course course=new Course();
		course.setFees(BigDecimal.valueOf(fees));
		course.setTitle(title);
		course.setDescription(description);
		if(image!=null) {
			course.setImage(image.getBytes());
		}
		teacher.addCourse(course);
		category=categoryDao.save(category);
		category.addCourse(course);
		courseDao.save(course);
		return "%s course successfully created."
				.formatted(title);
		
	}
	private Teacher getTeacherByName(String teacherName) {
		return teacherDao.findByUsername(teacherName)
				.orElseThrow(TeacherNotFoundException::new);
	}
	private Category getCategoryByName(String categoryName) {
		return categoryDao.findByCategoryName(categoryName)
				.orElseGet(() ->{
					Category category=new Category();
					category.setCategoryName(categoryName);
					return category;
				});
	}

}
