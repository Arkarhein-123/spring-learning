package com.solt;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.solt.service.EmployeeService;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.registerShutdownHook();
		EmployeeService employeeService = context.getBean(EmployeeService.class);
		employeeService.doAction();
	}

}
