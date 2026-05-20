package com.example.demo.service;

import java.util.Optional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

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
    
    public record PaymentSuccessRequest(String orderCode) {}
    public record OrderFailDto(String orderId, String reason) {}

    /**
     * Handles Success Tokens from the Payment processing microservice
     */
    @RabbitListener(queues = "payment_success_queue")
    @Transactional
    public void paymentSuccessListener(PaymentSuccessRequest request) {
        try {
            Optional<Order> orderOpt = orderDao.findByOrderCode(request.orderCode);
            if (orderOpt.isPresent()) {
                Order order = orderOpt.get();
                order.setStatus(OrderStatus.SUCCESS);
                orderDao.saveAndFlush(order);
                
                var orderSuccess = new PaymentSuccessRequest(order.getOrderCode());
                rabbitTemplate.convertAndSend(OrderRabbitConfig.EXCHANGE, "order.success", orderSuccess);
                System.out.println("SAGA SUCCESS: Order status updated to SUCCESS for: " + request.orderCode);
            }
        } catch (Exception e) {
            System.err.println("Error processing payment success token: " + e.getMessage());
        }
    }
    
    /**
     * Handles Saga Rollback Tokens coming out of the Inventory microservice
     */
    @RabbitListener(queues = "fail.order.queue") // Matches your OrderRabbitConfig.FAIL_ORDER_QUEUE literal
    @Transactional
    public void handleOrderFailureFromInventory(OrderFailDto failDto) {
        System.out.println("====== [START] Order Compensation Flow ======");
        System.out.println("Received Saga Rollback Event for Order ID: " + failDto.orderId());
        
        try {
            Optional<Order> orderOptional = orderDao.findByOrderCode(failDto.orderId());
            
            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                
                if (order.getStatus() == OrderStatus.CANCEL) { 
                    System.out.println("Order " + failDto.orderId() + " is already CANCELLED. Skipping.");
                    return;
                }
                
                order.setStatus(OrderStatus.CANCEL); 
                orderDao.saveAndFlush(order);
                System.out.println("SUCCESS: Order " + failDto.orderId() + " status set to CANCEL.");
            } else {
                System.err.println("WARNING: Order code not found in database: " + failDto.orderId());
            }
            
        } catch (Exception e) {
            System.err.println("CRITICAL ERROR inside Order failure consumer: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("====== [END] Order Compensation Flow ======");
    }
}