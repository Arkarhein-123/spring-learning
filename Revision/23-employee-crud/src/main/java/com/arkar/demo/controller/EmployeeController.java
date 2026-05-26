package com.arkar.demo.controller;

import com.arkar.demo.dto.EmployeeRequest;
import com.arkar.demo.dto.EmployeeResponse;
import com.arkar.demo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {
    private final EmployeeService employeeService;

    // localhost:8080/api/employees
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
        List<EmployeeResponse> response = employeeService.getAllEmployees();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create-employee")
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest request){
         EmployeeResponse response = employeeService.createEmployee(request);
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(response);
    }
}
