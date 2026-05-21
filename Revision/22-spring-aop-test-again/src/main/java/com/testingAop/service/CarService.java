package com.testingAop.service;

import com.testingAop.Car;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CarService {
    private Logger logger = Logger.getLogger(CarService.class.getSimpleName());

    public void driveCar(Car car){
        logger.info("My "+car.getName()+" car with the color of " + car.getColor()+" is driving ");
    }
}
