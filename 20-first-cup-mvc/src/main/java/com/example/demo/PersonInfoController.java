package com.example.demo;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/person-info")
public class PersonInfoController {

	// localhost:8080/person-info/show-info-v1?name=Arkar+Hein&hobby=Playing+game&city=Yangon
	@GetMapping("/show-info-v1")
	public String showInfoV1(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		String hobby = request.getParameter("hobby");
		int age = Integer.parseInt(request.getParameter("age"));
		String info = "%s who is %s years old lives in %s and hobby is %s".formatted(name, age, city, hobby);
		model.addAttribute("info", info);
		return "person-info";
	}

	@GetMapping("/show-info-v2")
	public String showInfoV2(@RequestParam("name") String name, @RequestParam("city") Optional<String> city,
			@RequestParam("hobby") Optional<String> hobby, @RequestParam(name = "age") Optional<Integer> age,
			Model model) {
		String info = "%s who is %s years old lives in %s and hobby is %s".formatted(name, age.orElse(22),
				city.orElse("Sagaing"), hobby.orElse("playing games"));
		model.addAttribute("info", info);
		return "person-info";
	}

	// localhost:8080/person-info/show-info-v3/name/john/age/22/hobby/eating/city/PaungByin
	@GetMapping(value = {
			"/show-info-v3/name/{name}/age/{age}/hobby/{hobby}/city/{city}",
			"/show-info-v3/name/{name}/age/{age}/hobby/{hobby}", 
			"/show-info-v3/name/{name}/age/{age}",
			"/show-info-v3/name/{name}"
			})
	public String showInfoV3(
			@PathVariable("name") String name,
			@PathVariable("age") Optional<Integer> age,
			@PathVariable("hobby") Optional<String> hobby,
			@PathVariable("city")Optional<String> city,
			Model model) {

		String info = "%s who is %s years old lives in %s and hobby is %s".formatted(name, age.orElse(25), city.orElse("Gwa"), hobby.orElse("Playing Football"));
		model.addAttribute("info", info);
		return "person-info";
	}

}
