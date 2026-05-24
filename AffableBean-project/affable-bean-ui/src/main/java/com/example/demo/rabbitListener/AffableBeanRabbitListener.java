package com.example.demo.rabbitListener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.order.success.OrderInfo;
import com.example.demo.order.success.OrderInfoRepository;
import com.example.demo.order.success.OrderStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AffableBeanRabbitListener {
	
	private final OrderInfoRepository orderInfoRepository;

	
	public record PaymentSuccessRequest(String orderCode, String sessionId) {}
	public record OrderFailRequest(String orderId, String message) {}
	public record OrderCancelDto(String orderCode, String message) {
	}
	
	@RabbitListener(queues = "order.fail.from.inventory.queue")
	public void orderFailListenerPaymentFail(OrderCancelDto dto) {
		changeOrderStatus(OrderStatus.ORDER_FAILURE,dto.orderCode,"ဿာျငInsufficinet amount from your Account..");
		
	}

	@RabbitListener(queues = "success.order.queue")
	public void orderSuccessListener(PaymentSuccessRequest request) {
	    System.out.println("Affable Bean UI : " + request.orderCode());  
	    changeOrderStatus(OrderStatus.ORDER_SUCCESS, request.orderCode,"Order Being Success!"); 	
	}
	
	@RabbitListener(queues = "fail.order.queue")
	public void orderFailListener(OrderFailRequest request) {
		System.out.println("Affable Bean UI : " + request.orderId());  
		changeOrderStatus(OrderStatus.ORDER_FAILURE,request.orderId,request.message);
	}
	
//	helper Method
	private void changeOrderStatus(OrderStatus orderStatus,String orderId,String message) {
		 orderInfoRepository.findByOrderId(orderId)
 		.ifPresentOrElse(order ->{
 			System.out.println("Order already existed.");
 			order.setOrderStatus(orderStatus);
 			orderInfoRepository.save(order);
 		}, () ->{
 			OrderInfo orderInfo = new OrderInfo();
 			orderInfo.setOrderId(orderId);
 			orderInfo.setOrderStatus(orderStatus);
 			orderInfo.setMessage(message);
 			orderInfoRepository.save(orderInfo);
 		});
	}
}
