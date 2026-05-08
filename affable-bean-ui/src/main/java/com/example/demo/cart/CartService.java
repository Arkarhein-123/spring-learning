package com.example.demo.cart;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
	private final CartRepository cartRepository;
	
	public Cart getMyCart() {
		return cartRepository.findById(getBrowserSessionId()).orElse(new Cart());
	}
	
	public void addToCart(CartItem cartItem) { 
		String sessionId = getBrowserSessionId(); 
		
		Cart cart = cartRepository.findById(sessionId).orElseGet(()->{
			Cart newCart = new Cart();
			newCart.setId(sessionId);
			return newCart;
		});
		cart.addItem(cartItem);
		
	}
	
	public  String getBrowserSessionId() {
		return RequestContextHolder.currentRequestAttributes()
				.getSessionId();
		
	}
}
