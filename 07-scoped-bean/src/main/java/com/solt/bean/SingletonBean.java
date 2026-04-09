package com.solt.bean;

import org.springframework.stereotype.Component;

@Component
public class SingletonBean {
	public SingletonBean() {
		System.out.println(this.getClass().getName()+":: constructor of singletonbean");
	}
}
