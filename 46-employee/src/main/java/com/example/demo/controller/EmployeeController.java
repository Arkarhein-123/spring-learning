package com.example.demo.controller;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.enitty.Employee;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.createEmployee(employeeDto);
    }

    @GetMapping("/{id}")
    public EmployeeDto finById(@PathVariable("id")Long id){
        return employeeService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable("id")Long id){
        employeeService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
