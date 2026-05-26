package com.example.demo.controller;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.enitty.Employee;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin
public class EmployeeController {
    private final EmployeeService employeeService;

    // localhost:8080/api/employees
    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }
}
