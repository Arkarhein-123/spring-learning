package com.nus.arkar.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nus.arkar.UserEntity;
import com.nus.arkar.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class AppController {
	private final UserRepository userRepository;
	
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String handleLogin(
			@RequestParam("userName")String userName,
			@RequestParam("password")String password,
			HttpSession session
			) {
		Optional<UserEntity> userOpt = userRepository.findByUserName(userName);
		if(userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
			UserEntity user = userOpt.get();
			session.setAttribute("scopedTarget.user", user);
			return "redirect:/dashboard";
		}
		
		return "redirect:/?error";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(
			HttpSession session, Model model
			) {
		UserEntity user = (UserEntity) session.getAttribute("scopedTarget.user");
		if(user == null) return "redirect:/";
		model.addAttribute("user",user);
		return "dashboard";
	}
	
	@GetMapping("/logout-confirm")
	public String showLogoutPage(HttpSession session, Model model) {
	    UserEntity user = (UserEntity) session.getAttribute("scopedTarget.user");
	    if (user == null) return "redirect:/";
	    
	    model.addAttribute("user", user);
	    return "logout"; // 
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); // Session is destroyed here
	    return "redirect:/?logout"; 
	}
	
}
