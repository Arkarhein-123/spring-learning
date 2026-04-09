package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ProvinceDao;
import com.example.demo.entity.Province;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentSubject;
import com.example.demo.entity.Subject;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	private final ProvinceDao provinceDao;
	
	@Transactional
	public void createDb() {
		Province p1 =new Province();
		p1.setProvinceName("Sule");		
		Province p2 = new Province();
		p2.setProvinceName("Latha");
		
		
		Student s1 = new Student("John","ISM");
		Student s2 = new Student("Htet", "Htet");
		Student s3 = new Student("Thomas","Kings");
		Student s4 = new Student("Richard","PISM");
		
		Subject sub1 = new Subject("Python",6, 100);
		Subject sub2 = new Subject("Groovy",3 , 150);
		Subject sub3 = new Subject("Spring",4,200);
		
		
//		add maping
		p1.addStudent(s1);
		p1.addStudent(s2);
		p2.addStudent(s3);
		p1.addStudent(s4);
		
		
//		s1 python 80
		StudentSubject stsub1 = new StudentSubject();
		stsub1.setMarks(80);
		s1.addStudentSubject(stsub1);
		sub1.addStudentSubject(stsub1);
		
		
//		s1 Groovy 95
		StudentSubject stsub2 = new StudentSubject();
		stsub2.setMarks(80);
		s1.addStudentSubject(stsub2);
		sub2.addStudentSubject(stsub2);
		
		
		
		StudentSubject stsub3 = new StudentSubject();
		stsub3.setMarks(85);
		s1.addStudentSubject(stsub3);
		sub3.addStudentSubject(stsub3);
		
		
		StudentSubject stsub4 = new StudentSubject();
		stsub4.setMarks(90);
		s2.addStudentSubject(stsub4);
		sub3.addStudentSubject(stsub4);
		
		
		StudentSubject stsub5 = new StudentSubject();
		stsub5.setMarks(60);
		s2.addStudentSubject(stsub5);
		sub3.addStudentSubject(stsub5);
		
		
		StudentSubject stsub6 = new StudentSubject();
		stsub6.setMarks(50);
		s2.addStudentSubject(stsub6);
		sub3.addStudentSubject(stsub6);
		
		
		StudentSubject stsub7 = new StudentSubject();
		stsub7.setMarks(90);
		s4.addStudentSubject(stsub7);
		sub3.addStudentSubject(stsub7);
		
		provinceDao.save(p1);
		provinceDao.save(p2);
		
		
		
		
	}
}
