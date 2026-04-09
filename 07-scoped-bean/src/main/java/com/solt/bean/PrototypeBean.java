package com.solt.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component @Scope("prototype") //to be prototype
public class PrototypeBean {
	public PrototypeBean() {
		System.out.println(this.getClass().getName()+ "::constructor of prototypebean");
	}
}
