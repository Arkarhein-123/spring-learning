package com.test;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CarService {
    private Logger logger = Logger.getLogger(CarService.class.getName());

    public void simulateCar(Car car){
        logger.info("My "+car.getName() + " car with the color of "+car.getColor()+ " is moving...");
    }
}
