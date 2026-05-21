package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.config.OrderRabbitConfig;
import com.example.demo.dao.OrderDao;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderRabbitListenerService {

	private final OrderDao orderDao;
	private final RabbitTemplate rabbitTemplate;

	public record PaymentSuccessRequest(String orderCode) {
	}

	public record OrderFailRequest(String orderId, String message) {
	}

	public record InventoryFailRequest(String orderId, String message) {
	}

	public record InventoryCompansationDto(String orderId, String messsage) {
	};

	public record OrderCancelDto(String orderCode, String message) {
	}

	private Order changeOrderStatus(String orderCode, OrderStatus orderStatus) {
		Order order = orderDao.findByOrderCode(orderCode).get();
		order.setStatus(orderStatus);
		orderDao.save(order);
		return order;
	}

	@RabbitListener(queues = "order.fail.from.inventory.queue")
	public void orderFailListenerPaymentFail(OrderCancelDto dto) {
		changeOrderStatus(dto.orderCode, OrderStatus.CANCEL);
		
	}

	@RabbitListener(queues ="inventory.compansation.queue")
	public void paymentFailListener(InventoryCompansationDto inventoryCompensationDto) {
		Order order = changeOrderStatus(inventoryCompensationDto.orderId(), OrderStatus.CANCEL);
		var orderCancel = new OrderCancelDto(order.getOrderCode(), inventoryCompensationDto.messsage());
		rabbitTemplate.convertAndSend(OrderRabbitConfig.EXCHANGE,
				OrderRabbitConfig.ORDER_CANCEN_INVENTORY_FAIL_BINDING_KEY, orderCancel);

	}

	@RabbitListener(queues = "payment_success_queue")
	public void paymentSuccessListener(PaymentSuccessRequest paymentRequest) {
		Order order = changeOrderStatus(paymentRequest.orderCode(), OrderStatus.SUCCESS);
		var orderSuccess = new PaymentSuccessRequest(order.getOrderCode());
		rabbitTemplate.convertAndSend(OrderRabbitConfig.EXCHANGE, "order.success", orderSuccess);
	}

	@RabbitListener(queues = "inventory.fail.queue")
	public void inventoryFailListener(InventoryFailRequest failRequest) {
		Order order = changeOrderStatus(failRequest.orderId(), OrderStatus.CANCEL);
		var orderFailReqeust = new OrderFailRequest(failRequest.orderId(), failRequest.message());
		rabbitTemplate.convertAndSend(OrderRabbitConfig.EXCHANGE, "order.failed", orderFailReqeust);

	}

}