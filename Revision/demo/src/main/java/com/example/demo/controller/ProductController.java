package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public String getProducts(Model model){
        model.addAttribute("products",productService.getProducts());
        return "products";
    }

    @PostMapping("/products")
    public String addProducts(
            @RequestParam("name")String name,
            @RequestParam("price")double price,
            Model model
    ){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        productService.addProduct(product);
        model.addAttribute("products",productService.getProducts());
        return "products";
    }

}
