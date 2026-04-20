package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.DispatcherServlet;


@Controller
@RequestMapping("/myapp")
public class HomeController {
	// uri, url
	
	// Get / Post / 
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
	
	@GetMapping("/")
	public String index() {
		return "forward:/myapp/home";
	}
	
	@GetMapping(value = "/home")
	public String home(Model model) {
		model.addAttribute("msg","Welcome MVC");
		return "pages/home";
	}
	
	@GetMapping("/hello")
	public String sayHello(Model model) {
		model.addAttribute("msg","Hello MVC");
		return "pages/home";
	}
	
	@GetMapping("/greet")
	public String sayGreet(Model model) {
		model.addAttribute("msg","Hello, how are you doing?");
		return "home";
	}
	
	@GetMapping("/say-something")
	public String saySomething(Model model) {
		model.addAttribute("msg","life is beautiful");
		return "pages/home";
	}
		
	@GetMapping("/say-something-extra")
	public String saySomethingExtra() {
		return "redirect:/myapp/say-something";
	}
}

