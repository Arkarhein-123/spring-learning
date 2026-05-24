package com.example.test;


import com.example.test.config.AppConfig;
import com.example.test.model.Car;
import com.example.test.service.CarService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) { // Fixed here
        System.out.println("Spring AOP Test....");
        var context =new AnnotationConfigApplicationContext(AppConfig.class);
        var carService = context.getBean(CarService.class);

        Car car = new Car("Toyota","Red");
        Car car2 = new Car("Suzuki","cian");

        String service = carService.simulateCar(car,car2);
        System.out.println("***************************");
        System.out.println(service);
        context.registerShutdownHook();

    }
}