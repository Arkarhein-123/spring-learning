package com.example.demo.dao;

import com.example.demo.enitty.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee,Long> {
}
