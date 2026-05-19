package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.OrderRabbitConfig;
import com.example.demo.dao.OrderDao;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;

@Service
public class OrderRabbitListenerService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public record PaymentSuccessRequest(String orderCode) {};
	public record InventoryFailRequest(String orderId) {}

	
	@RabbitListener(queues = "payment_success_queue")
	public void paymentSuccessListener(PaymentSuccessRequest request) {
		Order order = orderDao.findByOrderCode(request.orderCode).get();
		order.setStatus(OrderStatus.SUCCESS);
		orderDao.save(order);
		var orderSuccess = new PaymentSuccessRequest(order.getOrderCode());
		rabbitTemplate.convertAndSend(OrderRabbitConfig.EXCHANGE, "order.success",orderSuccess);
	}
	
	@RabbitListener(queues = "inventory.fail.queue")
	public void paymentFailureListener(InventoryFailRequest request) {
		Order order = orderDao.findByOrderCode(request.orderId).get();
		order.setStatus(OrderStatus.CANCEL);
		orderDao.save(order);
	}
}
