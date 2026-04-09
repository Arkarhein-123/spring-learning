package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ContractEmployee;

public interface ContractEmployeeDao extends JpaRepository<ContractEmployee, Long> {
	
}
