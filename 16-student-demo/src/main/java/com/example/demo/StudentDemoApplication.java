package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.service.StudentService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class StudentDemoApplication {

private final StudentService studentService;
	

	@Bean
	public ApplicationRunner runner() {
		return r ->{
			studentService.createDb();
			JPAUtil.checkData("select * from province");
			JPAUtil.checkData("select * from student");
			JPAUtil.checkData("select * from subject");
			JPAUtil.checkData("select * from student_subject");
		};
		
	}
	
	public static void main(String[] args) {
		System.out.println("Hello Dev Tool");
		
		SpringApplication.run(StudentDemoApplication.class, args);
	}

}
