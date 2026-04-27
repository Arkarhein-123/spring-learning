package com.example.demo.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeDao employeeDao;

    @GetMapping("/list-employees")
    public String listAllDepartments(Model model){
        model.addAttribute("employees",employeeDao.findAll());
        return "employee/employees";
    }

    public String departmentForm(Model model){
        model.addAttribute("employee",new Employee());
        return "";
    }
}
