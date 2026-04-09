package com.solt;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CustomBeanPostProcessor implements BeanPostProcessor{

	@Override
	public @Nullable Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println(beanName + "::BeanPostProcessor.postPorcessBeforeInitialization()");
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}
			
	@Override
	public @Nullable Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println(beanName + "::BeanPostProcessor.postProcessAfterInitialization()");
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
	
}
