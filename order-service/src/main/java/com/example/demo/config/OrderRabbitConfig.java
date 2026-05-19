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
public class OrderRabbitConfig {
	public static final String EXCHANGE ="saga.exchange";
	public static final String QUEUE = "order.queue";
	public static final String SUCCESS_QUEUE = "success.order.queue";
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE);
	}
	@Bean
	public Queue orderQueue() {
		return new Queue(QUEUE);
	}
	
	@Bean
	public Queue successOrderQueue() {
		return new Queue(SUCCESS_QUEUE);
	}
	
	@Bean
	public Binding successOrderBinding(@Qualifier("successOrderQueue")Queue successOrderQueue,TopicExchange exchange) {
		return BindingBuilder.bind(successOrderQueue)
				.to(exchange)
				.with("order.success");
	} 
	
	@Bean
	public  Binding  binding(@Qualifier("orderQueue")Queue orderQueue,TopicExchange exchange) {
		return BindingBuilder.bind(orderQueue)
				.to(exchange).with("order.created");
	}
	
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	
	
}
