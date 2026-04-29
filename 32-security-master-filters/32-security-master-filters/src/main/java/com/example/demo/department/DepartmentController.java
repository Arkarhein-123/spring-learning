package com.example.demo.department;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
	
	private final DepartmentDao departmentDao;
	
	@GetMapping("/delete-department")
	public String deleteDepartment(@RequestParam("id") long id) {
		departmentDao.deleteById(id);
		return "redirect:/list-departments";
	}
	
	@GetMapping("/department-form")
	public String departmentForm(Model model) {
		model.addAttribute("department", new Department());
		return "department/departmentForm";
	}
	
	@PostMapping("/department-form")
	public String saveDepartment(Department department, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "department/departmentForm";
		}
		departmentDao.save(department);
		return "redirect:/list-departments";
	}
	
	@GetMapping("/list-departments")
	public String listAllDepartments(Model model) {
		model.addAttribute("departments", departmentDao.findAll());
		return "department/departments";
	}

}
