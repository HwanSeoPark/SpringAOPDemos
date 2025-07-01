package com.pppspringaopdemos.pointcutapi.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.time.LocalDateTime;
import java.util.Arrays;

public class AuditTrailAdvice implements MethodInterceptor {

	@Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        Object[] args = invocation.getArguments();
        String timestamp = LocalDateTime.now().toString();

        System.out.println("[AuditTrailAdvice] Method: " + methodName);
        System.out.println("[AuditTrailAdvice] Args: " + Arrays.toString(args));
        System.out.println("[AuditTrailAdvice] Time: " + timestamp);

        return invocation.proceed();
    }
}
