package com.solt;

import org.springframework.stereotype.Component;

@Component
public class MySpringBean {
	public MySpringBean() {
		System.out.println(this.getClass().getSimpleName()+"::constructor of MySpringBean");
	}
}
