package com.pppspringaopdemos.aspectjsupport.declaringadvice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class TimingAspect {

    @Around("execution(* com.pppspringaopdemos.aspectjsupport.declaringadvice.service.MyService.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("Timer started for method: " + pjp.getSignature());

        // 타겟 클래스 메서드를 실제로 실행
        Object result = pjp.proceed();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Timer stopped for method: " + pjp.getSignature());
        System.out.println("Execution time: " + duration + " ms");

        return result;
    }
}