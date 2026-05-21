package com.testingAop;

import com.testingAop.service.CarService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testing Spring AOP");
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var carService = context.getBean(CarService.class);

        Car car = new Car("Toyota","Red");
        carService.driveCar(car);
    }
}
