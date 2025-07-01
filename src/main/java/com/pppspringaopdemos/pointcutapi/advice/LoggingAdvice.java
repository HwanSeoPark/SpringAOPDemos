package com.pppspringaopdemos.pointcutapi.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggingAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("[LoggingAdvice] : Before method: " + invocation.getMethod().getName());
        Object result = invocation.proceed();
        System.out.println("[LoggingAdviceAfter] : After method: " + invocation.getMethod().getName());
        return result;
    }
}