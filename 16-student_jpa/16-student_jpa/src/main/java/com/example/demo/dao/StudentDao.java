package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.StudentInfoRecord;
import com.example.demo.entity.Student;
import java.util.List;


public interface StudentDao extends JpaRepository<Student, Long>{
	@Query("""
			select new com.example.demo.StudentInfoRecord(
				s.id,
				p.provinceName,
				s.name,
				s.school,
				stusub.marks,
				sub.fees,
				sub.subjectName
			) from Province p join p.students s
			join s.studentSubjects stusub join stusub.subject sub
			""")
	List<StudentInfoRecord> findAllStudentInfo();
	
	@Query("""
			select new com.example.demo.StudentInfoRecord(
				s.id,
				p.provinceName,
				s.name,
				s.school,
				stusub.marks,
				sub.fees,
				sub.subjectName
			) from Province p join p.students s
			join s.studentSubjects stusub join stusub.subject sub 
			where sub.subjectName = :subjectName and 
			stusub.marks = (select max(stsu.marks) from StudentSubject stsu)
			""")
	List<StudentInfoRecord> findMaxMarksStudentInfo(@Param("subjectName") String subjectName);
	
	Optional<Student> findByName(String name);
	List<Student> findBySchool(String school);
	@Query("""
			select s from Student s where s.name = :name
			""")
	Optional<Student> findByNameV2(@Param("name") String name);
	
	
}
