package com.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.logging.Logger;

@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* com.test.CarService.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info("Before Method Execution....");
        var object = joinPoint.proceed();
        logger.info("After Method Excution....");
        return object;
    }
}
