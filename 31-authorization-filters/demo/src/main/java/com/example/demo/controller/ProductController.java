package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/home") // read / write / admin
    public String home(){
        return "Welcome Product Home!";
    }
    @PostMapping("/products/delete") // admin / write
    public String deleteProduct(){
        return "Delete Products!";
    }
    @PostMapping("/products/audit") //only admin
    public String auditProduct(){
        return "Audit Products!";
    }
}
