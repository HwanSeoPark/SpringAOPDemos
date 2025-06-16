package com.pppspringaopdemos.usingtheproxyfactorybeantocreateaopproxies.jbproperties.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class LoggingBeforeAdvice implements MethodBeforeAdvice {
	
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Before1 method: " + method.getName()); // <-- Cross-Cutting Concerns
    }
}