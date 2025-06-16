package com.pppspringaopdemos.pointcutapi.pointcut;


import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import com.pppspringaopdemos.pointcutapi.advice.RetryAdvice;


@SuppressWarnings("serial")
public class MyPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

    public MyPointcutAdvisor() {
        setAdvice(new RetryAdvice()); // 어드바이스 설정
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "performOperation".equals(method.getName()); // 이 메서드에만 어드바이스 적용
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> clazz.getName().startsWith("com.pppspringaopdemos.pointcutapi.service");
    }
}
