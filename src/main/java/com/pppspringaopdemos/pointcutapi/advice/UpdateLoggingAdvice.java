package com.pppspringaopdemos.pointcutapi.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UpdateLoggingAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("[UpdateLoggingAdvice] Executing: " + invocation.getMethod().getName());
        return invocation.proceed();
    }
}