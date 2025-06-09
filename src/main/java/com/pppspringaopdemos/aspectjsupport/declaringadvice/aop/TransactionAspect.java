package com.pppspringaopdemos.aspectjsupport.declaringadvice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {

	// (..): 메서드의 파라미터 개수와 타입에 상관없이 일치합니다. 즉, 파라미터가 0개일 수도 있고, 여러 개일 수도 있습니다.
    @Around("execution(* com.pppspringaopdemos.aspectjsupport.declaringadvice.service.TransactionalService.*(..))")
    public Object manageTransaction(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Transaction started");
        Object result = pjp.proceed();
        System.out.println("Transaction committed");
        return result;
    }
}