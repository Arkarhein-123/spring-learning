package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.cart.CartItem;
import com.example.demo.cart.CartService;
import com.example.demo.dto.ProductDto;
import com.example.demo.service.AffableBeanService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/affable-bean/ui")
public class AffableBeanController {
	private final AffableBeanService affableBeanService;
	private final CartService cartService;
	
	private String currentCategoryName;
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/checkout-view")
	public String checkoutView() {
		return "checkout-view";
	}
	// /affable-bean/ui/checkout-view
	@PostMapping("/checkout-view")
	public String processCheckout(
			@RequestParam("username")String username,
			@RequestParam("password")String password,
			@RequestParam("accountNumber")String accountNumber
			) {
		affableBeanService.checkout(username, password, accountNumber);
		
		return "success";
	}
	
	// /affable-bean/ui/cart-view
	@GetMapping("/cart-view")
	public String viewCart(Model model) {
		model.addAttribute("cartItems",cartService.getMyCart().getItems());
		return "cart-view";
	}
	
	// /affable-bean/ui/add-to-cart?id=2
	@GetMapping("/add-to-cart")
	public String addToCart(@RequestParam("id") Long id) {
	    ProductDto productDto = affableBeanService.getProductById(id);
	    cartService.addToCart(productDto);
	    
	    // Fallback if currentCategoryName is null
	    String category = (currentCategoryName != null) ? currentCategoryName : "dairy";
	    return "redirect:/affable-bean/ui/products?name=" + category;
	}
	
	// /affable-bean/ui/increase-quantity?id=
	@GetMapping("/increase-quantity")
	public String increaseQty(@RequestParam("id")Long id) {
		cartService.increateQty(id);
		return "redirect:/affable-bean/ui/cart-view"; 
	}
	// /affable-bean/ui/decrease-quantity?id=
	@GetMapping("/decrease-quantity")
	public String DecreaseQty(@RequestParam("id")Long id) {
		cartService.decreaseQty(id);
		return "redirect:/affable-bean/ui/cart-view";
	}
	
	// /affable-bean/ui/products?name=
	@GetMapping("/products")
	public String listProducts(@RequestParam("name")String name,Model model) {
		model.addAttribute("products",affableBeanService.listAllProducts(name));
		currentCategoryName = name;
		return "products";
	}
	
	// /affable-bean/ui/clear-cart
	@GetMapping("/clear-cart")
	public String clearCart() {
		cartService.clearCart();
		return "redirect:/affable-bean/ui/cart-view";
	}
	
	
	//  /affable-bean/ui/remove-cart-item?id=
	@GetMapping("/remove-cart-item")
	public String removeCartItem(@RequestParam("id")Long id) {
		cartService.removeCartItem(id);
		return "redirect:/affable-bean/ui/cart-view";
	}
	
	@ModelAttribute("totalPrice")
	public double totalPrice() {
		return cartService.getMyCart().getItems() 
				.stream()
				.map(item -> item.getQuantity() * item.getPrice())
				.mapToDouble(Double::doubleValue)
				.sum();
	}
	
	
	@ModelAttribute("itemSize")
	public int itemSize() {
		return cartService
				.getMyCart()
				.getItems()
				.stream() 
				.map(CartItem::getQuantity)  // Stream<Integer>
				.mapToInt(Integer::intValue) // IntStream
				.sum();
	}
	
	@ModelAttribute("cartSize")
	public int cartSize() {
		return cartService.getMyCart().getItems().size();
	}
	
}
