package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PartTimeEmployee;

public interface PartTimeEmployeeDao extends JpaRepository<PartTimeEmployee, Long>{

}
