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
public class OrderRabbitConfig {
	public static final String EXCHANGE ="saga.exchange";
	public static final String QUEUE = "order.queue";
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE);
	}
	@Bean
	public Queue orderQueue() {
		return new Queue(QUEUE);
	}
	@Bean
	public  Binding  binding(Queue ordQueue,TopicExchange exchange) {
		return BindingBuilder.bind(ordQueue)
				.to(exchange).with("order.created");
	}
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	
	
}
