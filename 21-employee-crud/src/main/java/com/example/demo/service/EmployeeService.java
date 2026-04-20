package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Employee;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeDao employeeDao;
	
	public List<Employee> findAllEmployee(){
		return employeeDao.findAll();
	}
	
	public void saveEmployee(Employee employee) {
		employeeDao.save(employee);
	}
}
