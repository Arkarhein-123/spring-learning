package com.example.demo.service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.enitty.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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

    public EmployeeDto createEmployee(EmployeeDto dto){
        return toDto(employeeDao.save(toEmployee(dto)));
    }

    public EmployeeDto findById(Long id) {
        return toDto(employeeDao.findById(id).orElseThrow());
    }

    public void deleteById(Long id){
        employeeDao.deleteById(id);
    }

    private EmployeeDto toDto(Employee employee){
        EmployeeDto dto = new EmployeeDto();
        BeanUtils.copyProperties(employee,dto);
        return dto;
    }

    private Employee toEmployee(EmployeeDto dto){
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto,employee);
        return employee;
    }

}
