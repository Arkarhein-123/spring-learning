package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @GetMapping("/")
    public String home(){
        return "product";
    }
    @PostMapping("/add-product")
    public void saveProduct(@RequestParam("product")String product){
        logger.info("You add ::"+product);
    }

}
