package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;









@Configuration
public class RabbitMqConfig {
	public static final String EXCHANGE ="saga.exchange";
	public static final String PAYMENT_QUEUE ="payment.processing.queue";
	public static final String ROUTING_KEY_INVENTORY_SUCCESS="inventory.stock.decreased";
	@Bean
	public TopicExchange sagaExchange() {
		return new TopicExchange(EXCHANGE);
	}
	@Bean
	public Queue paymentQueue() {
		return new Queue(PAYMENT_QUEUE);
	}
	@Bean
	public Binding bindingPayment(
			Queue paymentQueue,
			TopicExchange sagaExchange) {
		return BindingBuilder.bind(paymentQueue)
				.to(sagaExchange)
				.with(ROUTING_KEY_INVENTORY_SUCCESS);
	}
	
	
	
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	

}
