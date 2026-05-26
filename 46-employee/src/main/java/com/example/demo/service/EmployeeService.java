package com.example.demo.service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.enitty.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tools.jackson.databind.util.BeanUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDao employeeDao;

    public List<EmployeeDto> findAll() {
        List<Employee> employees = employeeDao.findAll();
        return employees.stream()
                .map(this::toDto)
                .toList();
    }

    private EmployeeDto toDto(Employee employee){
        EmployeeDto dto = new EmployeeDto();
//        dto.setFirstName(employee.getFirstName());
//        dto.setLastName(employee.getLastName());
//        dto.setEmail(employee.getEmail());
//        dto.setSalary(employee.getSalary());
        BeanUtils.copyProperties(employee,dto);
        return dto;
    }

}
