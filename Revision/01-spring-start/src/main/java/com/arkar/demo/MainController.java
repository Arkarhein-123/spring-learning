package com.arkar.demo;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	@RequestMapping("/home")
	public String home(
			@RequestParam("name")String name,
			@RequestParam("color")String color,
			Model model
			) {
		model.addAttribute("name",name);
		model.addAttribute("color",color);
		return "home.html";
	}
	
	@GetMapping(value= {
			"/home2/name/{name}/color/{color}",
			"/home2/name/{name}"
	})
	public String home2(
				@PathVariable("name")String name,
				@PathVariable("color")Optional<String> color,
				Model page
			) {
		page.addAttribute("name",name);
		if(color.isPresent()) {
			page.addAttribute("color",color.get());
		}
		page.addAttribute("color",color.orElse("blue"));
		return "home";
	}
}
