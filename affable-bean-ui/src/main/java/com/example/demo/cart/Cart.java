package com.example.demo.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("Cart")
public class Cart {
	@Id
	private String id;
	private List<CartItem> items = new ArrayList<>();
	
	
	public void addItem(CartItem cartItem) {
		items.add(cartItem);
	}
	
	public void removeItem(Long productId) {
		items.removeIf(item -> item.getId() == (productId));
	}
}
