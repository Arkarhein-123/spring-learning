package com.example.demo;

import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.core.env.Environment;

public class EmployeeBeanRegistrar implements BeanRegistrar {

	@Override
	public void register(BeanRegistry registry, Environment env) {
		registry.registerBean("employee", Employee.class);
		
	}
}
