package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Province;
import com.example.demo.entity.Student;

public interface ProvinceDao extends JpaRepository<Province, Long>{
	@Query("""
			select s from Student s where s.province.provinceName = :name
			""")
	List<Student> findStudentsByProvinceName(@Param("name") String name );
	
	@Query("""
			select p from Province p join p.students s where s.name = :name
			""")
	List<Province> findProvinceByStudentName(@Param("name") String name);
}
