package com.example.test.service;

import com.example.test.model.Car;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CarService {
    private Logger logger = Logger.getLogger(CarService.class.getName());
    public String simulateCar(Car car1,Car car2){
        logger.info("My car "+car1.getName()+" with "+car1.getColor()+" color is driving...");
        logger.info("My car "+car2.getName()+" with "+car2.getColor()+" color is driving...");
        return "SUCCESS";
    }
}
