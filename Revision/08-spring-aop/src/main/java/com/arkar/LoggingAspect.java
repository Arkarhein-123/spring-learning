package com.arkar;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* com.arkar.service.*.*(..))")
    public void log(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info("Before Executed....");
        joinPoint.proceed();
        logger.info("After Executed....");
    }
}
