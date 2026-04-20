package com.jdc.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/myApp")
public class GiveInfo {
//	@RequestMapping(value="/home",method = RequestMethod.GET)
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("message","How are you doing?");
		return "home";
	}
}
