package com.demo;

import com.demo.config.AppConfig;
import com.demo.pattern.FormalGreeting;
import com.demo.pattern.IGreet;
import com.demo.pattern.VerboseGreetingProxy;
import com.demo.service.CurrencyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) { // Fixed signature
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Spring will now find the bean
        CurrencyService currencyService = context.getBean(CurrencyService.class);
        currencyService.changeCurrency(100);

        context.registerShutdownHook();
    }
}
