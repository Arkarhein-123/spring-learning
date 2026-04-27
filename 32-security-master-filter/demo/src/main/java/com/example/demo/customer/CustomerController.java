package com.example.demo.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerDao customerDao;

    @GetMapping("/list-customers")
    public String listAllCustomers(Model model){
        model.addAttribute("customers",customerDao.findAll());
        return "customer/customers";
    }

    @GetMapping("/customer-form")
    public String customerForm(Model model){
        model.addAttribute("customer", new Customer());
        return "customer/customer-form";
    }

    @PostMapping("/customer-form")
    public String saveCustomer(Customer customer, BindingResult result){
        if(result.hasErrors()){
            return "customer/customer-form";
        }
        customerDao.save(customer);
        return "redirect:/list-customers";
    }

    @GetMapping("/delete-customer")
    public String deleteCustomer(@RequestParam("id")Long id){
        customerDao.deleteById(id);
        return "redirect:/list-customers";
    }
}
