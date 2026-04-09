package com.solt.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SpringBean1 implements InitializingBean, DisposableBean{
	private SpringBean2 springBean2;
	
	
	public SpringBean1() {
		System.out.println(this.getClass().getSimpleName()
				+"::constructor.");
	}
	
	@Autowired
	public void setSpringBean2(SpringBean2 springBean2) {
		System.out.println(springBean2.getClass().getSimpleName()
				+" injected by setter."
				);	
		this.springBean2 = springBean2;
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println(this.getClass().getSimpleName()+"::postConstruct()");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(this.getClass().getSimpleName()
				+"::InitializingBean.afterpropertiesSet()"
				);
	}
	
	
	public void customInit() {
		System.out.println(this.getClass().getSimpleName()
				+"customInit()");
	}
	
	public String greet(String name) {
		return "Hello!"+ name + ".";
	}
	
	@PreDestroy
	public void preDestory() {
		System.out.println(this.getClass().getSimpleName()
				+"::preDestory()");
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println(this.getClass().getSimpleName()+"::DisposableBean.destroy() ");
		
	}
	
	public void customDestory() {
		System.out.println(this.getClass().getSimpleName()+"::customDestory()");
	}
	
}
