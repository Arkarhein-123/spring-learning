package spel;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spel.bean.SpringBean1;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context  = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		context.registerShutdownHook();
		SpringBean1 springBean1 = context.getBean(SpringBean1.class);
		springBean1.print();
	}

}
