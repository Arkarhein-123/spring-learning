package com.example.demo.employee;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import static com.example.demo.security.SecurityConfig.*;
@Controller
@RequiredArgsConstructor
public class EmployeeController {
	
	private final EmployeeDao employeeDao;
	
	@PreAuthorize("hasAnyRole('SUPER_ADMIN','EMPLOYEES_ADMIN')")
	@GetMapping("/delete-employee")
	public String deleteEmployee(@RequestParam("id") long id) {
		employeeDao.deleteById(id);
		return "redirect:/list-employees";
	}
	@PreAuthorize("hasAnyRole('SUPER_ADMIN','EMPLOYEES_ADMIN')")
	@PostMapping("/employee-form")
	public String saveEmployee(Employee employee, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "employee/employee-form";
		}
		employeeDao.save(employee);
		return "redirect:/list-employees";
	}
	@PreAuthorize("hasAnyRole('SUPER_ADMIN','EMPLOYEES_ADMIN')")
	@GetMapping("/employee-form")
	public String employeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "employee/employeeForm";
	}
	@PreAuthorize("hasAnyRole('SUPER_ADMIN','EMPLOYEES_ADMIN')")
	@GetMapping("/list-employees")
	public String listAllEmployees(Model model) {
		model.addAttribute("employees", employeeDao.findAll());
		return "employee/employees";
	}

}
