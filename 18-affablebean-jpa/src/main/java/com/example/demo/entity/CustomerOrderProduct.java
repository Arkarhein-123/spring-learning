package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(OrderProductId.class)
public class CustomerOrderProduct {
	@Id
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	@Id
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_order_id")
	private CustomerOrder customerOrder;
}
