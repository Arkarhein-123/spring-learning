package com.example.demo.customer;

import org.springframework.security.access.annotation.Secured;

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
public class CustomerController {
	
	private final CustomerDao customerDao;
	@Secured("ROLE"+SUPER_ADMIN)
	@GetMapping("/delete-customer")
	public String deleteCustomer(@RequestParam("id") long id) {
		customerDao.deleteById(id);
		return "redirect:/list-customers";
	}
	@Secured("ROLE"+SUPER_ADMIN)
	@GetMapping("/customer-form")
	public String customerForm(Model model) {
		model.addAttribute("customer", new Customer()); // creates form object
		return "customer/customerForm"; // see form
	}
	@Secured("ROLE"+SUPER_ADMIN)
	@PostMapping("/customer-form")
	public String saveCustomer(Customer customer, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "customer/customerForm";
		}
		customerDao.save(customer);
		return "redirect:/list-customers";
	}
	@Secured({"ROLE_"+CUSTOMERS_READ,"ROLE_"+SUPER_ADMIN})
	@GetMapping("/list-customers")
	public String listAllCustomers(Model model) {
		model.addAttribute("customers", customerDao.findAll());
		return "customer/customers";
	}

}
