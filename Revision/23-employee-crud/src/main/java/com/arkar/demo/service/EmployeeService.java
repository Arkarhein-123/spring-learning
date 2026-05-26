package com.arkar.demo.service;

import com.arkar.demo.dao.EmployeeDao;
import com.arkar.demo.dto.EmployeeRequest;
import com.arkar.demo.dto.EmployeeResponse;
import com.arkar.demo.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDao employeeDao;

    public List<EmployeeResponse> getAllEmployees(){
        return employeeDao.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public EmployeeResponse createEmployee(EmployeeRequest request){
        Employee employee = new Employee();
        BeanUtils.copyProperties(request,employee);
        Employee savedEmployee = employeeDao.save(employee);
        return toDto(employee);
    }
    private EmployeeResponse toDto(Employee employee){
        EmployeeResponse response = new EmployeeResponse();
        BeanUtils.copyProperties(employee,response);
        return response;
    }
}
