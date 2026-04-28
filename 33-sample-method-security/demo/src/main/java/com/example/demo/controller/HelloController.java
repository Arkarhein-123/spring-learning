package com.example.demo.controller;

import com.example.demo.model.Document;
import com.example.demo.model.Employee;
import com.example.demo.service.DocumentService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    @Autowired
    private NameService nameService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DocumentService documentService;

    @GetMapping("/document/{code}")
    public Document getDocument(@PathVariable("code")String code){
        return documentService.getDocumentV2(code);
    }


    @GetMapping("/employee/details/{name}")
    public Employee getEmployeeDetails(@PathVariable("name")String name){
        return employeeService.getEmployeeDetails(name);
    }

    @GetMapping("/secret-names")
    public List<String> getSecretName(@RequestParam("name")String name){
        return nameService.getSecretNames(name);
    }

    @GetMapping("/hello")
    public String getName(){
        return nameService.getName();
    }


}
