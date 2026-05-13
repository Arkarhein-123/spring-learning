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
	public static final String EXCHANGE = "order_exchange";
	public static final String INV_QUEUE = "inventory_queue";
	public static final String PAY_QUEUE = "payment_queue";
	
	
//	QueExhange  and topicExchange
	@Bean
	public TopicExchange  exchange() {
		return new TopicExchange(EXCHANGE);
	}
	
	@Bean
	public Queue invQueue() {
		return new Queue(INV_QUEUE);
	}
	
	@Bean
	public Queue payQueue() {
		return new Queue(PAY_QUEUE);
	}
	
	@Bean
	public Binding bindInv(Queue invQueue, TopicExchange exchange) {
		return BindingBuilder.bind(invQueue).to(exchange).with("order.#");
	}
	
	@Bean 
	public Binding bindPay(Queue payQueue, TopicExchange exchange) {
		return BindingBuilder.bind(payQueue).to(exchange).with("order.#");
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
