package com.example.demo.department;

import org.springframework.stereotype.Controller;
import static com.example.demo.security.SecurityConfig.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
	
	private final DepartmentDao departmentDao;
	@RolesAllowed(SUPER_ADMIN)
	@GetMapping("/delete-department")
	public String deleteDepartment(@RequestParam("id") long id) {
		departmentDao.deleteById(id);
		return "redirect:/list-departments";
	}
	@RolesAllowed({SUPER_ADMIN,DEPARTMENTS_READ})
	@GetMapping("/department-form")
	public String departmentForm(Model model) {
		model.addAttribute("department", new Department());
		return "department/departmentForm";
	}
	@RolesAllowed({SUPER_ADMIN,DEPARTMENTS_READ})
	@PostMapping("/department-form")
	public String saveDepartment(Department department, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "department/departmentForm";
		}
		departmentDao.save(department);
		return "redirect:/list-departments";
	}
	@RolesAllowed({SUPER_ADMIN,DEPARTMENTS_READ})
	@GetMapping("/list-departments")
	public String listAllDepartments(Model model) {
		model.addAttribute("departments", departmentDao.findAll());
		return "department/departments";
	}

}
