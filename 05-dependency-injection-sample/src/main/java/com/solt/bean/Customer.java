package com.solt.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class Customer {
	private String name = "Ye Htet Aung";
//	@Autowired  // Field DI
	private final Address add;
	
	public Customer(final Address address) {
		this.add = address;
	}

//	@Autowired  // Constructor DI
//	public Customer(Address add) {
//		super();
//		this.add = add;
//		System.out.println("One Arugment constructor");
//		System.out.println("Address Class In Constructor: "+ add.getClass().getSimpleName()+"\n");
//	}
//	
//	public Customer(Address address, @Autowired(required = false)String name) {
//		this.add = address;
//		System.out.println("Two Arugment Constructor : "+add.getClass().getSimpleName()+"\n");
//	}
	
//	public Customer() {
//		System.out.println("Address Class In Constructor: "+ add);
//	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("PostConstruct: "+ add.getClass().getSimpleName()+"\n");
	}
	
	// Setter DI
//	@Autowired
//	public void setAdd(Address add) {
//		System.out.println("Setter Injection Address Class: "+add.getClass().getSimpleName()+"\n");
//		this.add = add;
//	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String showInfo() {
		return "%s lives in %s.".formatted(name, add.getStreetName());
	}
	
	
}
