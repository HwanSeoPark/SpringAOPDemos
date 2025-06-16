package com.pppspringaopdemos.adviceapiinspring.advices;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

// ThrowsAdvice는 가상메서드가 한개도 없다
public class SimpleThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(Method m, Object[] args, Object target, Exception ex) {
        System.out.println("Exception thrown in method: " + m.getName() + ", exception: " + ex.getMessage());
    }
}