package com.example.demo.cart;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.example.demo.dto.ProductDto;

@Service
public class CartService {

    private final CartRepository cartRepository;

    // 1. Clean Constructor: Only inject dependencies
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    
    public void increateQty(Long id) {
    	Cart cart = getMyCart();
		cart.getItems().stream()
			.map(item -> {
				if(item.getId() == id) {
					item.setQuantity(item.getQuantity()+1);
				}
				return item;
			}).collect(Collectors.toSet());
		cartRepository.save(cart);
	}

    public void decreaseQty(Long id) {
    		Cart cart = getMyCart();
    		cart.getItems().stream()
    			.map(item -> {
    				if(item.getId() == id && item.getQuantity() > 1) {
    					item.setQuantity(item.getQuantity() - 1);
    				}
    				return item;
    			}).collect(Collectors.toSet());
    		cartRepository.save(cart);
    }
    
    public Cart getMyCart() {
        String sessionId = getBrowserSessionId();
        return cartRepository.findById(sessionId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setId(sessionId);
                    // Don't necessarily need to save yet, just return it
                    return newCart;
                });
    }

    public void addToCart(ProductDto productDto) {
        String sessionId = getBrowserSessionId();
        
        // 2. Find or Create logic
        Cart cart = cartRepository.findById(sessionId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setId(sessionId);
                    return newCart;
                });

        cart.addItem(toCartItem(productDto));
        
        // 3. Save to Redis/DB
        cartRepository.save(cart);
    }

    private CartItem toCartItem(ProductDto productDto) {
        return new CartItem(
                productDto.id(),
                productDto.name(),
                productDto.price(),
                1
        );
    }

    private String getBrowserSessionId() {
        return RequestContextHolder
                .currentRequestAttributes()
                .getSessionId();
    }
    
    public void clearCart() {
    		cartRepository.deleteById(getBrowserSessionId());
    }
    
    
    public void removeCartItem(Long productId) {
    		Cart cart = getMyCart();
        
        Set<CartItem> updatedItems = cart.getItems().stream()
            .filter(item -> !item.getId().equals(productId))
            .collect(Collectors.toSet());
        
        // Update the cart and save
        cart.setItems(updatedItems);
        cartRepository.save(cart);
    }
   
    
}
