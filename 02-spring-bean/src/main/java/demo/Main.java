package demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import demo.bean.text.AppConfig;

/*
 * 1. xml
 * 2. Java Config
 * 3. ComponentScan component
 * 4. Functional Bean Registration
 * */

public class Main {
	public static void main(String[] args) {
//		ApplicationContext context = 
//					new FileSystemXmlApplicationContext("beans.xml");
		
//		ClassPathXmlApplicationContext context = 
//				new ClassPathXmlApplicationContext("beans.xml");
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		Employee employee = context.getBean(Employee.class);
		System.out.println("Name: %s %s "
				.formatted(employee.getFirstName(), employee.getLastName()));
//		((FileSystemXmlApplicationContext)context).close();
		context.close();
		
		
		
	}
}
