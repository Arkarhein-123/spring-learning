package com.example.demo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.example.demo.cart.CartItem;
import com.example.demo.cart.CartService;
import com.example.demo.client.AuthClient;
import com.example.demo.client.LoginDto;
import com.example.demo.client.LoginResponse;
import com.example.demo.client.OrderClient;
import com.example.demo.client.OrderItem;
import com.example.demo.client.OrderRequest;
import com.example.demo.client.ProductClient;
import com.example.demo.dto.ProductDto;
import com.example.demo.order.success.OrderInfo;
import com.example.demo.order.success.OrderInfoRepository;
import com.example.demo.order.success.OrderStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AffableBeanService {
	private final ProductClient productClient;
	private final AuthClient authClient;
	private final OrderClient orderClient;
	private final CartService cartService;
	private final OrderInfoRepository orderInfoRepository;
	
	public record PaymentSuccessRequest(String orderCode, String sessionId) {}
	
	private OrderItem toOrderItem(CartItem cartItem) {
		return new OrderItem(cartItem.getName(),cartItem.getQuantity(),cartItem.getPrice());
	}
	
	public List<ProductDto> listAllProducts(String name){
		return productClient.listAllProducts(name);
	}
	
	public ProductDto getProductById(Long id) {
		return productClient.getProductById(id);
	}
	
	
	public String checkout(String username,String password, String accountNumber, String orderId) {
		LoginDto loginDto = new LoginDto(username,password);
		ResponseEntity<LoginResponse> response = authClient.login(loginDto);
		if(response.getStatusCode().is2xxSuccessful() && Objects.nonNull(response.getBody().token())) {
			String jwtToken = "Bearer" + response.getBody().token();
			List<OrderItem> orderItems = cartService
					.getMyCart()
					.getItems()
					.stream()
					.map(this::toOrderItem)
					.toList();
			OrderRequest request = new OrderRequest(username,orderItems,accountNumber,orderId);
			orderClient.createOrder(jwtToken, request);
			
		}else {
			System.out.println("Transport layer Error!");
		}
			
		return "checkout Success";
	}
	private String getBrowserSessionId() {
        return RequestContextHolder
                .currentRequestAttributes()
                .getSessionId();
    }
	
	@RabbitListener(queues = "success.order.queue")
	public void orderSuccessListener(PaymentSuccessRequest request) {
	    System.out.println("Affable Bean UI : " + request.orderCode());  

	    orderInfoRepository.findByOrderId(request.orderCode)
	    		.ifPresentOrElse(order ->{
	    			System.out.println("Order already existed.");
	    		}, () ->{
	    			OrderInfo orderInfo = new OrderInfo();
	    			orderInfo.setOrderId(request.orderCode);
	    			orderInfo.setOrderStatus(OrderStatus.ORDER_SUCCESS);
	    			orderInfoRepository.save(orderInfo);
	    		});
	}
	
}
