package com.example.demo.employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
	
	private final EmployeeDao employeeDao;
	
	@GetMapping("/delete-employee")
	public String deleteEmployee(@RequestParam("id") long id) {
		employeeDao.deleteById(id);
		return "redirect:/list-employees";
	}
	
	@PostMapping("/employee-form")
	public String saveEmployee(Employee employee, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "employee/employee-form";
		}
		employeeDao.save(employee);
		return "redirect:/list-employees";
	}
	
	@GetMapping("/employee-form")
	public String employeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "employee/employeeForm";
	}
	
	@GetMapping("/list-employees")
	public String listAllEmployees(Model model) {
		model.addAttribute("employees", employeeDao.findAll());
		return "employee/employees";
	}

}
