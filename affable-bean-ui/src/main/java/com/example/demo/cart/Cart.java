package com.example.demo.cart;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("Cart")
public class Cart {
	@Id
	private String id;
	private Set<CartItem> items = new HashSet<>();
	
	public void addItem(CartItem cartItem) {
		items.add(cartItem);
	}
	
	public void removeItem(Long productId) {
		items.removeIf(item -> item.getId() == (productId));
	}
	
	
}
