package com.example.demo.department;

import com.example.demo.customer.CustomerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentDao departmentDao;

    @GetMapping("/list-departments")
    public String listAllDepartments(Model model){
        model.addAttribute("departments",departmentDao.findAll());
        return "department/departments";
    }

    @GetMapping("/department-form")
    public String departmentForm(Model model){
        model.addAttribute("department",new Department());
        return "department/department-form";
    }
    @PostMapping("/department-form")
    public String addDepartment(Department department, BindingResult result){
        if(result.hasErrors()){
            return "department/department-form";
        }
        departmentDao.save(department);
        return "redirect:/list-departments";
    }
    @GetMapping("/delete-department")
    public String deleteDepartment(@RequestParam("id")Long id){
        departmentDao.deleteById(id);
        return "redirect:/list-departments";
    }


}
