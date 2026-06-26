package com.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CurrencyServiceAspect {
    @Pointcut("target(com.demo.service.CurrencyService)")
    public void targetPointCut(){

    }

    @Before("targetPointCut()")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("Before Join Point :"+joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName());
    }
}
