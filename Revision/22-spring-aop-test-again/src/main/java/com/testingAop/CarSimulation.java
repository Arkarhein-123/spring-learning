package com.testingAop;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CarSimulation {
    private Logger logger = Logger.getLogger(CarSimulation.class.getSimpleName());

    public void driveCar(Car car){
        logger.info("My "+car.getName()+" car with the color of " + car.getColor()+" is driving ");
    }
}
