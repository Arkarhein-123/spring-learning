package com.test;

import com.example.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class testMain {
    public static void main() {
        System.out.println("Testing Aspect Oriented Programming...");
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var carService = context.getBean(CarService.class);

        Car car = new Car("Toyota Lancruiser","Black");
        carService.simulateCar(car);
    }
}
