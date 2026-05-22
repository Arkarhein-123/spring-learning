package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // http://localhost:8080/products
    @GetMapping("/products")
    public Flux<ProductDto> findAll(){
        return productService.findAll();
    }
    // http://localhost:8080/products/1
    @GetMapping("/products/{id}")
    public Mono<ProductDto> findById(@PathVariable String id){
        return productService.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Product Not Found Excepiton!")));
    }

    @PostMapping("/insert/product")
    public Mono<ProductDto> insertProduct(@RequestBody ProductDto productDto){
        return productService.insertProduct(productDto);
    }

    @PutMapping("/update/product/{id}")
    public Mono<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto){
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/delete/product/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return productService.deleteProduct(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND ,"Id Not Found...")));
    }
}
