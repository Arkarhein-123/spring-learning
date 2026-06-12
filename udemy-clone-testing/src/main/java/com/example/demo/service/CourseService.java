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
	
	
	private final CategoryDao categoryDao;
	private final CourseDao courseDao;
	private final TeacherDao teacherDao;

	public List<CourseDto> findAllCourses(){
		return courseDao.findAllCourses();
	}

	public List<CourseDto>  findAllCoursesByTeacherName(String username){
		return courseDao.findAllCoursesByTeacherName(username);
	}

	
	@Transactional
	public String createCourse(
			String title,
			double fees,
			String description,
			int studentCount,
			MultipartFile image,
			String categoryName,
			String teacherName
			) throws IOException{
		
		
		Category category = getCategoryByName(categoryName);	
		Teacher teacher = getTeacherByName(teacherName);
		
        Course course = new Course();
        course.setFees(BigDecimal.valueOf(fees));
        course.setTitle(title);
        course.setDescription(description);
        if(image!=null) {
        	course.setImage(image.getBytes());
        }
        teacher.addCourse(course);
        category.addCourse(course);
        category = categoryDao.save(category);
        courseDao.save(course);
      

		return "%s course Successfully Saved".formatted(title);
		
		
	}
	
	private Category getCategoryByName(String name) {
		
		
          return categoryDao.findByCategoryName(name)
        		  .orElseGet(() -> {
        			  Category category = new Category();
        			  
        			  category.setCategoryName(name);
        			  
        			  System.out.println(category.getCategoryName());
        			  return category;
        		  });
          
	}
	
	private Teacher getTeacherByName(String name) {
		
		return teacherDao.findByUsername(name)
				.orElseThrow(TeacherNotFoundException:: new);
	}
	
	

}
