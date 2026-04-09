package com.example.demo.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public record OrderProductId(
			Long product,
			Long customerOrder
		){

}
