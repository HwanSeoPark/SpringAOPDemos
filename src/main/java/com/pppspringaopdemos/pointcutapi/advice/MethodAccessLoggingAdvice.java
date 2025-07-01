package com.pppspringaopdemos.pointcutapi.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.time.LocalDateTime;

public class MethodAccessLoggingAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        String className = invocation.getThis().getClass().getSimpleName();
        String timestamp = LocalDateTime.now().toString();

        System.out.println("[MethodAccessLoggingAdvice] "
                + timestamp + " - "
                + className + "#" + methodName + " called");

        return invocation.proceed();
    }
}