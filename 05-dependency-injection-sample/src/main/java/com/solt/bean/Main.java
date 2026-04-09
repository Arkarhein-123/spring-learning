package com.solt.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
//		Address address = new Address();
//		address.setStreetName("No.180, Padamya Street");
		
//		=============== java SE ====================
//		Customer customer = new Customer();
//		customer.setName("Arkar Hein");
//		customer.setAdd(address);		
//		System.out.println(customer.showInfo());
		
		
//		================= Spring =================
//		ClassPathXmlApplicationContext context = 
//				new ClassPathXmlApplicationContext("beans.xml");
//		context.registerShutdownHook();
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);	
		context.registerShutdownHook();
		
		Customer customer2 = context.getBean(Customer.class);
		System.out.println(customer2.showInfo());
	}
}
