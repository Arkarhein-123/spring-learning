package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.EmployeeInput;
import com.example.demo.entity.Employee;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeDao employeeDao;
	
	private Employee toEmployee(EmployeeDto dto) {
		return new Employee(dto.name(),dto.department(),dto.salary());
	}
	private EmployeeDto toEmployeeDto(Employee employee) {
		return new EmployeeDto(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary());
	}
	
	public List<EmployeeDto> findAllEmployees(){
		return employeeDao
				.findAll()
				.stream()
				.map(this::toEmployeeDto)
				.toList(); 
	}
	
	public EmployeeDto findEmployeeById(Long id) {
		return employeeDao
				.findById(id)
				.map(this::toEmployeeDto).orElse(null);
	}
	public EmployeeDto createEmployee(EmployeeInput employee) {
		Employee employeeEntity = employeeDao.save(new Employee(
				employee.name(),
				employee.department(),
				employee.salary()
				)
			);	
		return toEmployeeDto(employeeEntity);
	}
	public EmployeeDto updateEmployee(Long id, EmployeeInput employee) {
		if(employeeDao.existsById(id)) {
			Employee employeeEntity = new Employee(
						employee.name(),
						employee.department(),
						employee.salary()
					);
			employeeEntity.setId(id);
			employeeEntity = employeeDao.save(employeeEntity);
			return toEmployeeDto(employeeEntity);
		}
		return null;
		
	}
	public Boolean deleteEmployeeById(Long id) {
		if(employeeDao.existsById(id)) {
			employeeDao.deleteById(id);
			return true;
		}
		return false;
	}
}
