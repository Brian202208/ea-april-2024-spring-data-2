package com.brianmugalu.springdata2.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class QueryTrackingAspect {

    private QueryCounter queryCounter;

    @Autowired
    public QueryTrackingAspect(QueryCounter queryCounter) {
        this.queryCounter = queryCounter;
    }

    @Before("execution(* com.example.package.GeneralController.*(..))")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        // Increment the query count before method execution
        queryCounter.incrementQueryCount();
    }

    @AfterReturning(pointcut = "execution(* com.example.package.GeneralController.*(..))", returning = "result")
    public void afterMethodExecution(JoinPoint joinPoint, Object result) {
        // Log or perform actions after method execution
        // You can finalize tracking execution time, data transfer, memory usage, etc. here
        System.out.println("Method execution finished: " + joinPoint.getSignature().getName());
    }
}
