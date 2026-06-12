//package com.example.demo.service;
//
//import java.util.Map;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//
//	private final AuthenticationManager authenticationManager;
//
//	public Map<String, String> login(String username, String password) {
//		var auth = new UsernamePasswordAuthenticationToken(username, password);
//		
//		var authentication = authenticationManager.authenticate(auth);
//		
//		StringBuilder sb = authentication
//				.getAuthorities()
//				.stream()
//				.map(GrantedAuthority::getAuthority) // get authority
//				.map(r -> { // transform data
//					StringBuilder s = new StringBuilder();
//					s.append(r);
//					return s;
//				}).findAny().get();
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		return Map.of("username", authentication.getName(), "roles", sb.toString());
//	}
//
//}

package com.example.demo.service;

import java.awt.DefaultFocusTraversalPolicy;
import java.math.BigDecimal;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.TeacherDao;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentEducation;
import com.example.demo.entity.Teacher;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleDao roleDao;
    private final StudentDao studentDao;
    private final TeacherDao teacherDao;
    
    
    private Role getRoleByName(String roleName) {
    	
    	return roleDao.findByRoleName(roleName)
    			.orElseGet(() ->{
    				Role role = new Role();
    				role.setRoleName(roleName);
    				return role;
    			});
    	
    }
    
    
    public String register(RegisterDto register) {

        return switch (register.userType()) {

            case "teacher" -> {

                var teacher = new Teacher(
                        register.username(),
                        passwordEncoder.encode(register.password()),
                        register.email(),
                        BigDecimal.valueOf(0),
                        register.education()
                );

                teacher.setSkills(register.skills());

                Role teacherRole = getRoleByName("ROLE_TEACHER");
                teacher.addRole(roleDao.save(teacherRole));

                teacherDao.save(teacher);

                yield "%s registered as Teacher successfully"
                        .formatted(register.username());
            }

            case "student" -> {
                var student = new Student(
                   register.username(), 
                     passwordEncoder.encode(register.password()), 
                    register.email(), 
                    register.address(),
                    StudentEducation.valueOf(register.studentEducation())
                    );
                Role studentRole = getRoleByName("ROLE_STUDENT");
                student.addRole(roleDao.save(studentRole));
                studentDao.save(student);
                yield "%s registered successfully!".formatted(register.username());
              }

            default -> throw new IllegalArgumentException(
                    "Unexpected userType: " + register.userType()
            );
        };
    }

    
    
    public Map<String, String> login(String username, String password) {

        var auth = new UsernamePasswordAuthenticationToken(username, password);

        try {
            var authentication = authenticationManager.authenticate(auth);

            System.out.println("LOGIN SUCCESS");

            StringBuilder sb = authentication
                    .getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .map(r -> {
                        StringBuilder s = new StringBuilder();
                        s.append(r);
                        return s;
                    })
                    .findAny()
                    .orElse(new StringBuilder("NO_ROLE"));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return Map.of(
                    "username", authentication.getName(),
                    "roles", sb.toString()
            );

        } catch (Exception e) {
            System.out.println("LOGIN FAILED");
            e.printStackTrace();
            throw e; // important so Spring still returns 401
        }
    }
}


// and change to
// stream
// builder