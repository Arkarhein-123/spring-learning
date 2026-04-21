package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.scoped.ApplicationScopedBean;
import com.example.demo.scoped.RequestScopedBean;
import com.example.demo.scoped.SessionScopedBean;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScopedController {
	private final RequestScopedBean requestScopedBean;
	private final SessionScopedBean sessionScopedBean;
	private final ApplicationScopedBean applicationScopedBean;
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("requestValue",requestScopedBean.getValue());
		model.addAttribute("sessionValue",sessionScopedBean.getValue());
		model.addAttribute("applicationValue",applicationScopedBean.getValue());
		return "home";
	}
	
	@PostMapping("/add-request-value")
	public String setRequestValue(@RequestParam("requestValue")int value) {
		requestScopedBean.setValue(value);
		return "forward:/";
	}
	
	@PostMapping("add-session-value")
	public String setSessionValue(@RequestParam("sessionValue")int value) {
		sessionScopedBean.setValue(value);
		return "redirect:/";
	}
	
	@PostMapping("add-application-value")
	public String setApplicationValue(@RequestParam("applicationValue")int value) {
		applicationScopedBean.setValue(value);
		return "redirect:/";
	}
}
