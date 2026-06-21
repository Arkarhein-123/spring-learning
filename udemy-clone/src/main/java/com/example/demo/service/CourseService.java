package com.example.demo.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.exception.AlreadyEnrolledCourseException;
import com.example.demo.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.CourseDto;
import com.example.demo.exception.TeacherNotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
	private final CourseDao courseDao;
	private final CategoryDao categoryDao;
	private final TeacherDao teacherDao;
	private final StudentDao studentDao;
	private final StudentEnrolledCourseDao studentEnrolledCourseDao;
	private final SiteOwnerDao siteOwnerDao;


	public String addLessons(CourseLesson courseLesson, Long courseId){
		Course course = getCourse(courseId);
		course.addCourseLesson(courseLesson);
		courseDao.saveAndFlush(course);
		return "%s is Successfully added to %s course".formatted(courseLesson.lessonName(), courseId);
	}

	public List<CourseLesson> findAllLessonsByCourseId(long courseId){
		return getCourse(courseId).getCourseLessons();
	}

	public List<CourseDto> getAllEnrolledCourses(String username){
		return studentEnrolledCourseDao.findCoursesByLoggedInUserName(username);
	}
	
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

	@Transactional
	public String enrolledCourse(List<Long> courseIds, String studentName){
		Student student = findStudentByName(studentName);
		String orderId = generateOrder(studentName);
		for (Long courseId : courseIds){
			Optional<StudentEnrollCourse> alreadyEnrolled = studentEnrolledCourseDao.testAlreadyEnrolledCourse(studentName,courseId);
			if(alreadyEnrolled.isPresent()){
				throw new AlreadyEnrolledCourseException(studentName,courseId);
			}

			Course course = getCourse(courseId);
			course.setStudetCount(course.getStudetCount()+1);
			StudentEnrollCourse studentEnrollCourse = new StudentEnrollCourse(orderId, LocalDate.now(),0);
			student.addEnrolledCourse(studentEnrollCourse);
			course.addEnrolledCourse(studentEnrollCourse);
			studentEnrolledCourseDao.save(studentEnrollCourse);

//			Calculate - course->StudentCount , teacher->networth, siteOwner->PlatFormShare
			SiteOwner siteOwner = getSiteOwner();
			Teacher teacher = course.getTeacher();
			double tenPercent = course.getFees().doubleValue() * 0.1;
			double nintyPercent = course.getFees().doubleValue() * 0.9;
			teacher.setNetWorth(teacher.getNetWorth().add(BigDecimal.valueOf(nintyPercent)));
			siteOwner.setPlatformShare(siteOwner.getPlatformShare().add(BigDecimal.valueOf(tenPercent)));
		}
		return "%s enrolled successfully with orderId: %s".formatted(studentName,orderId);
	}

	private SiteOwner getSiteOwner(){
		return siteOwnerDao.findById(3L).orElseThrow(() -> new RuntimeException("SiteOwner with ID 3L not found in database!"));
	}

	private Student findStudentByName(String username){
		return studentDao.findByUsername(username).orElseThrow(StudentNotFoundException::new);
	}

	private Course getCourse(long id){
		return courseDao.findById(id).get();
	}

//	Helper Class
	private String generateOrder(String username){
		SecureRandom random = new SecureRandom();
		int code = random.nextInt(100000) + 100000;
		StringBuilder sb = new StringBuilder();
		sb
				.append(username.toUpperCase())
			.append(code);
		return sb.toString();
	}
}
