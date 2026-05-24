package com.example.test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());
    @Around("execution(* com.example.test.service.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Method Name "+methodName+" with parameters "+
                Arrays.asList(args)+ " will execute...");
        Object returnByMethod = joinPoint.proceed();
        logger.info("Method executed and returned "+ returnByMethod);
        return returnByMethod;
    }
}
