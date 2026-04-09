package com.solt;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.solt.service.ReportService;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
//		context.register(AppConfig.class);
//		context.registerShutdownHook();
//		context.getEnvironment().setActiveProfiles("file");
//		context.refresh();
		
		ReportService service = context.getBean(ReportService.class);
		service.doAction();
	}
}
