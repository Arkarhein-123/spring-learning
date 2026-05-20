package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentRabbitMqConfig {
	public static final String EXCHANGE = "saga.exchange";
	public static final String PAYMENT_QUEUE = "payment_success_queue";
	public static final String PAYMENT_SUCCESS_ROUTING_KEY= "payment.success";
	public static final String PAYMENT_FAIL_QUEUE = "payment.fail.queue";
	public static final String PAYMENT_FAIL_ROUTING_KEY = "payment.fail";
	
	@Bean
	public TopicExchange sagaExchange() {
		return new TopicExchange(EXCHANGE);
	}
	
	// Payment Success Queue Start ******************
	@Bean
	public Queue paymentQueue() {
		return new Queue(PAYMENT_QUEUE);
	}
	
	@Bean 
	public Binding binding(TopicExchange sagaExchange,@Qualifier("paymentQueue") Queue paymentQueue) {
		return BindingBuilder.bind(paymentQueue)
				.to(sagaExchange)
				.with("payment.success");
	}
	// Payment Success Queue End ********************
	
	
	// Payment Fail queue start *********************
	@Bean 
	public Queue paymentFailQueue() {
		return new Queue(PAYMENT_FAIL_QUEUE);
	}
	
	@Bean
	public Binding paymentFailBinding(TopicExchange sagaExchange,@Qualifier("paymentFailQueue") Queue paymentFailQueue) {
		return BindingBuilder.bind(paymentFailQueue)
				.to(sagaExchange)
				.with(PAYMENT_FAIL_ROUTING_KEY);
	}
	// Payment Fail End ******************************
	
	
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
