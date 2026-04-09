package com.solt;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.solt.bean.SpringBean1;
import com.solt.bean.SpringBean2;
import com.solt.bean.SpringBean3;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		context.registerShutdownHook();
		
		System.out.println("--------------------------");
		
//		SpringBean1 ob1 = context.getBean(SpringBean1.class);
//		SpringBean2 ob2 = context.getBean(SpringBean2.class);
//		SpringBean3 ob3 = context.getBean(SpringBean3.class);
	}
}
