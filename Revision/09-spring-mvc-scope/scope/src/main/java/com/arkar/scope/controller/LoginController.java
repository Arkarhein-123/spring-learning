package com.arkar.scope.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String loginGet(){
        return "login";
    }

    @PostMapping("/")
    public String loginPost(
            @RequestParam("username")String username,
            @RequestParam("password")String password,
            Model model
    ){
        boolean isLogin = true;
        if(isLogin){
            model.addAttribute("message","You are now login!");
        }else{
            model.addAttribute("message","Login Failed!");
        }
        return "login";
    }
}
