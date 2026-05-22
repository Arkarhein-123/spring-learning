package com.example.demo.service;

import com.example.demo.dao.ProductDao;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;

    public Flux<ProductDto> findAll(){
        return productDao.findAll()
                .map(this::toDto);
    }

    public Mono<ProductDto> findById(String id){
        return productDao.findById(id)
                .map(this::toDto);
    }

    public ProductDto toDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public Product toProduct(ProductDto productDto){
        return new Product(
                productDto.name(),
                productDto.description(),
                productDto.price()
        );
    }

    public Mono<ProductDto> insertProduct(ProductDto productDto){
        return Mono.just(productDto)
                .map(this::toProduct)
                .flatMap(productDao::save)
                .map(this::toDto);
    }

    public Mono<ProductDto> updateProduct(String id, ProductDto productDto){
        return productDao.findById(id)
                .flatMap(p -> Mono.just(productDto)
                        .map(this::toProduct)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(productDao::save)
                .map(this::toDto);
    }

    public Mono<Void> deleteProduct(String id){
        return productDao.deleteById(id);
    }
}
