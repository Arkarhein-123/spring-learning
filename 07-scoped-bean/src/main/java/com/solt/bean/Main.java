package com.solt.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		context.registerShutdownHook();
//		
//		SingletonBean ob1 = context.getBean(SingletonBean.class);
//		SingletonBean ob2 = context.getBean(SingletonBean.class);
//		
//		System.out.println(ob1);
//		System.out.println(ob2);
//		
//		boolean isSameObject = ob1.equals(ob2);
//		System.out.println("Ob1 and ob2 are the same? :  "+isSameObject);
//		
		PrototypeBean p1 = context.getBean(PrototypeBean.class);
		PrototypeBean p2 = context.getBean(PrototypeBean.class);
		
//		System.out.println(p1);
//		System.out.println(p2);
//		
//		isSameObject = p1 == p2;
//		System.out.println("p1 and p2 are the same? : "+ isSameObject);
		
		
	}
}
