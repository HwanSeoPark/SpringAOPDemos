package com.pppspringaopdemos.aspectjsupport.declaringadvice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Aspect
//@Component
public class ExecutionCountingAspect {

    // thread-safed 카운터를 위한 AtomicInteger
    private AtomicInteger executionCount = new AtomicInteger(0);

    @Around("execution(* com.pppspringaopdemos.aspectjsupport.declaringadvice.service.SampleService.*(..))")
    public Object countExecutions(ProceedingJoinPoint pjp) throws Throwable {
        // 메서드 실행 전 카운터 증가
        int currentCount = executionCount.incrementAndGet();
        System.out.println("ExecutionCountingAspect: Execution count before method: " + currentCount);

        // 메서드를 실제로 실행
        Object result = pjp.proceed();

        // 메서드 실행 후 카운터 값 출력
        System.out.println("ExecutionCountingAspect: Execution count after method: " + executionCount.get());

        return result;
    }

    public int getExecutionCount() {
        return executionCount.get();
    }
}