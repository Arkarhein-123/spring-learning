package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/sell")
    public List<Product> sellProducts(){
        // mutable list
//        List<Product> products = new ArrayList<>();
//        products.add(new Product("beer","john"));
//        products.add(new Product("candy","john"));
//        products.add(new Product("chocolate","mary"));

//        inmutable list
        List<Product> products = List.of(
                new Product("beer","john"),
                new Product("candy","john"),
                new Product("chocolate","mary")
        );
        return productService.sellProducts(products);
    }
    @GetMapping("/search")
    public List<Product> searchProduct(){
        return productService.searchProduct();
    }

}
