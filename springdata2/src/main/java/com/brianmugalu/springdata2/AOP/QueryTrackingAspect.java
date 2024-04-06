package com.brianmugalu.springdata2.AOP;

import com.brianmugalu.springdata2.AOP.QueryCounter;


import com.brianmugalu.springdata2.AOP.QueryCounter;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@RequiredArgsConstructor
public class QueryTrackingAspect {
    private final QueryCounter queryCounter;
    private final ConcurrentHashMap<String, Long> queryExecutionTimes = new ConcurrentHashMap<>();
    private long totalExecutionTime = 0;

    @Before("execution(* com.brianmugalu.springdata2.controller.*.*(..))")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        // Increment the query count before method execution
        queryCounter.incrementQueryCount();

        // Capture timestamp before method execution
        queryExecutionTimes.put(joinPoint.getSignature().toShortString(), System.currentTimeMillis());
    }

    @AfterReturning(pointcut = "execution(* com.brianmugalu.springdata2.controller.*.*(..))", returning = "result")
    public void afterMethodExecution(JoinPoint joinPoint, Object result) {
        // Log or perform actions after method execution
        // You can finalize tracking execution time, data transfer, memory usage, etc. here
        String signature = joinPoint.getSignature().toShortString();
        Long startTime = queryExecutionTimes.remove(signature);
        if (startTime != null) {
            long executionTime = System.currentTimeMillis() - startTime;
            totalExecutionTime += executionTime;
            // Track memory usage
            long memoryUsed = getMemoryUsage();
            System.out.println("Method execution finished: " + joinPoint.getSignature().getName() +
                    ", Query count: " + queryCounter.getQueryCount() +
                    ", Execution time (ms): " + executionTime +
                    ", Memory used (bytes): " + memoryUsed);
        } else {
            System.out.println("Method execution finished: " + joinPoint.getSignature().getName() +
                    ", Query count: " + queryCounter.getQueryCount() +
                    ", Execution time (ms): Could not calculate (start time not found)");
        }
    }

    // Method to get the total execution time
    public long getTotalExecutionTime() {
        return totalExecutionTime;
    }

    // Method to track memory usage
    private long getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Request garbage collection to ensure accurate memory usage
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
