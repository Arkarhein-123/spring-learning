package demo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import demo.bean.Employee;

public class Main {
	public static void main(String[] args) {
//		Employee employee = new Employee();
//		employee.setFirstName("Arkar");
//		employee.setLastName("Hein");
		
		BeanFactory context  = new AnnotationConfigApplicationContext(AppConfig.class);
		Employee employee = context.getBean(Employee.class);

		System.out.println("Name: %s %s"
				.formatted(employee.getFirstName(),employee.getLastName()));
		
//		AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(AppConfig.class);
//		Employee employee2 = context.getBean(Employee.class);
//		context2.registerShutdownHook();
	}

}
