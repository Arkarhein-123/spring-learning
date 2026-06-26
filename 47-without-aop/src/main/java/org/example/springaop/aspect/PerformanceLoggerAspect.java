package org.example.springaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class PerformanceLoggerAspect {
    @Pointcut("@annotation(org.example.withoutaop.aspect.PerformanceLogger)")
    public void annotationPointCut(){}

    @Around("annotationPointCut()")
    public Object performanceAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime=System.currentTimeMillis();
        try{
            return joinPoint.proceed();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            long endTime=System.currentTimeMillis();
            long elipseTime = (endTime - startTime);
            System.out.println("%s method take times %s milliseconds."
                    .formatted(joinPoint.getSignature().getName(),elipseTime));
        }
        return null;
    }
}