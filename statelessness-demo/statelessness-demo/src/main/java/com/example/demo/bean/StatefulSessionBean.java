package com.example.demo.bean;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Getter;
import lombok.Setter;

@Component
@SessionScope
@Getter
@Setter
public class StatefulSessionBean {
	private int counter;
	
	public void increase() {
		this.counter += 1; 
	}
	
}
