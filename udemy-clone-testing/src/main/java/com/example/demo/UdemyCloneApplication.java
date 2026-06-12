package com.example.demo;

import java.math.BigDecimal;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.SiteOwnerDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.TeacherDao;
import com.example.demo.entity.Course;
import com.example.demo.entity.Role;
import com.example.demo.entity.SiteOwner;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentEducation;
import com.example.demo.entity.Teacher;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class UdemyCloneApplication {
	
	private final TeacherDao teacherDao;
	private final StudentDao studentDao;
	private final RoleDao roleDao;
	private final SiteOwnerDao siteOwnerDao;
	private final PasswordEncoder passwordEncoder;
	
	public Role getRoleByName(String roleName) {
		
		return roleDao.findByRoleName(roleName).orElseGet(() -> {
			Role role = new Role();
			role.setRoleName(roleName);
			return role;
		});
	}
	
	@Bean
	@Profile("dev")
	public ApplicationRunner runner() {
		
		return r -> {
			
			Role teacherRole = getRoleByName("ROLE_TEACHER");
			
			Teacher teacher1 = new Teacher("mary", 
					passwordEncoder.encode("12345"), 
					"mary@gmail.com",BigDecimal.valueOf(0),
					"MSC"
					);
			teacher1.addSkill("Java");
			teacher1.addSkill("Groovy");
			teacher1.addSkill("CS");
			teacher1.addRole(roleDao.save(teacherRole));
			teacherDao.save(teacher1);
			
			Role studentRole = getRoleByName("ROLE_STUDENT");
			Student student  = new Student(
					"mike",passwordEncoder.encode("12345"), 
					"mike@gmail.com", "No11. Strand Road", StudentEducation.UNDERGRADUATE);
			
			student.addRole(roleDao.save(studentRole));
			studentDao.save(student);
			
			Role siteOwnerRole = getRoleByName("ROLE_SITEOWNER");
			SiteOwner siteOwner = new SiteOwner(
					"william",passwordEncoder.encode("12345"),"william@gmail.com"
					);

			siteOwner.setPlatFormShare(BigDecimal.valueOf(0));
			siteOwner.addRole(roleDao.save(siteOwnerRole));
			siteOwnerDao.save(siteOwner);
			
		};
	}

	
	public static void main(String[] args) {
		SpringApplication.run(UdemyCloneApplication.class, args);
	}

}
