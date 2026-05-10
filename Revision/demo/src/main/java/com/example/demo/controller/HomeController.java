package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
//    @GetMapping("/home")
//    public String home(
//            @RequestParam(name="color",required = false)String color,
//            @RequestParam(name = "username",required = false)String username,
//            Model model
//    ){
//        model.addAttribute("username",username);
//        model.addAttribute("color",color);
//        return "home";


    @GetMapping({
            "/home/{username}",
            "/home/{username}/{color}"
    })
    public String greet(
            @PathVariable String username,
            @PathVariable String color,
            Model model
){
        model.addAttribute("username",username);
        model.addAttribute("color",color);
        return "home";
    }
}
