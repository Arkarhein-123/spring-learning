package org.example.simplerestapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.simplerestapi.dto.EmployeeDto;
import org.example.simplerestapi.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    @PatchMapping(value = "/{id}",consumes = MediaType.TEXT_PLAIN_VALUE)
    public EmployeeDto changeEmailEmployee(@PathVariable("id")long id,
                                           @RequestBody String email){
        return employeeService.changeEmailEmployee(id, email);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id")long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(
            @PathVariable("id")long id,
            @RequestBody EmployeeDto employeeDto){
        return employeeService.updateEmployee(id, employeeDto);
    }
    // /api/employees/1
    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable("id")long id){
        return employeeService.getEmployeeById(id);
    }
    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.createEmployee(employeeDto);
    }

    @GetMapping
    public List<EmployeeDto> listEmployee(){
        return employeeService.listEmployee();
    }

}
