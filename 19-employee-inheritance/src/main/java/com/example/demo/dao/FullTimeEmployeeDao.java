package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.FullTimeEmployee;

public interface FullTimeEmployeeDao extends JpaRepository<FullTimeEmployee, Long>{

}
