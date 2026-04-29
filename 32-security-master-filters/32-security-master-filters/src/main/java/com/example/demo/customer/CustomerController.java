package com.example.demo.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerDao customerDao;
	
	@GetMapping("/delete-customer")
	public String deleteCustomer(@RequestParam("id") long id) {
		customerDao.deleteById(id);
		return "redirect:/list-customers";
	}
	
	@GetMapping("/customer-form")
	public String customerForm(Model model) {
		model.addAttribute("customer", new Customer()); // creates form object
		return "customer/customerForm"; // see form
	}
	
	@PostMapping("/customer-form")
	public String saveCustomer(Customer customer, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "customer/customerForm";
		}
		customerDao.save(customer);
		return "redirect:/list-customers";
	}
	
	@GetMapping("/list-customers")
	public String listAllCustomers(Model model) {
		model.addAttribute("customers", customerDao.findAll());
		return "customer/customers";
	}

}
