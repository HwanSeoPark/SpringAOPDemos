package com.pppspringaopdemos.pointcutapi.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MethodCallCountingAdvice implements MethodInterceptor {

    private final ConcurrentHashMap<String, AtomicInteger> methodCallCounts = new ConcurrentHashMap<>();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        methodCallCounts
                .computeIfAbsent(methodName, k -> new AtomicInteger(0))
                .incrementAndGet();

        System.out.println("[MethodCallCountingAdvice] " + methodName +
                " was called " + methodCallCounts.get(methodName).get() + " times");

        return invocation.proceed();
    }
}