package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ProvinceDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Province;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentSubject;
import com.example.demo.entity.Subject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	private final ProvinceDao provinceDao;
	private final StudentDao studentDao;
	
	public void findStudentQuery() {
		System.out.println("FindByName");
		studentDao.findByNameV2("John")
			.ifPresent(System.out::println);
		System.out.println("FindBySChool");
		studentDao.findBySchool("ISM").forEach(System.out::println);;
		System.out.println("Find All Student Info ::");
		studentDao.findAllStudentInfo().stream().forEach(System.out::println);
		System.out.println("Find The highest mark Student: " + studentDao.findMaxMarksStudentInfo("Java"));
		System.out.println("Find Student By Province Name : ");
		provinceDao.findStudentsByProvinceName("Sule").forEach(System.out::println);;
		System.out.println("Province By Student Name::::::::::::: ");
		provinceDao.findProvinceByStudentName("John").forEach(System.out::println);
	}
	

	@Transactional
	public void createDb() {
		Province p1 = new Province();
		p1.setProvinceName("Sule");
		Province p2 = new Province();
		p2.setProvinceName("Latha");
		
		Student s1 = new Student("John","ISM");
		Student s2 = new Student("Mary", "ISY");
		Student s3 = new Student("Thomas","KINGS");
		Student s4 = new Student("Richard","PISM");
		
		Subject sub1 = new Subject("Python",6,100);
		Subject sub2 = new Subject("Groovy",3,150);
		Subject sub3 = new Subject("Java",6,200);
		
		p1.addStudent(s1);
		p1.addStudent(s2);
		p2.addStudent(s3);
		p2.addStudent(s4);
		
		StudentSubject stsub1= new StudentSubject();
		stsub1.setMarks(80);
		s1.addStudentSubject(stsub1);
		sub1.addStudentSubjects(stsub1);
		
		StudentSubject stsub2= new StudentSubject();
		stsub2.setMarks(95);
		s1.addStudentSubject(stsub2);
		sub2.addStudentSubjects(stsub2);
		
		StudentSubject stsub3= new StudentSubject();
		stsub3.setMarks(98);
		s1.addStudentSubject(stsub3);
		sub3.addStudentSubjects(stsub3);
		
		StudentSubject stsub4= new StudentSubject();
		stsub4.setMarks(85);
		s2.addStudentSubject(stsub4);
		sub1.addStudentSubjects(stsub4);
		
		StudentSubject stsub5= new StudentSubject();
		stsub5.setMarks(90);
		s2.addStudentSubject(stsub5);
		sub3.addStudentSubjects(stsub5);
		
		StudentSubject stsub6= new StudentSubject();
		stsub6.setMarks(80);
		s3.addStudentSubject(stsub6);
		sub3.addStudentSubjects(stsub6);
		
		StudentSubject stsub7= new StudentSubject();
		stsub7.setMarks(92);
		s4.addStudentSubject(stsub7);
		sub3.addStudentSubjects(stsub7);
		
		provinceDao.save(p1);
		provinceDao.save(p2);
	}

}
