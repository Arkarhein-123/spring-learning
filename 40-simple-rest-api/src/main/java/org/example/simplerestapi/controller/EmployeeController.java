package org.example.simplerestapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.simplerestapi.dto.EmployeeDto;
import org.example.simplerestapi.dto.Employees;
import org.example.simplerestapi.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE})
    public EmployeeDto createEmployee(@RequestBody @Validated EmployeeDto employeeDto){
        return employeeService.createEmployee(employeeDto);
    }

    @GetMapping(value = "/list/{type}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Employees> listEmployees(@PathVariable("type")String type){
        return switch (type){
            case "json" -> {
                yield ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(employeeService.listEmployees());
            }
            case "xml" ->{
                yield ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_XML)
                        .body(employeeService.listEmployees());
            }
            default -> {
               yield   ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        };
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public List<EmployeeDto> listEmployee(){
        return employeeService.listEmployee();
    }

    @GetMapping(value = "/list",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Employees listEmployees(){
        return employeeService.listEmployees();
    }
}
