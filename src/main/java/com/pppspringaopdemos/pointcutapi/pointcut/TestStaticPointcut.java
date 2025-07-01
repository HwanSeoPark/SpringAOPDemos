package com.pppspringaopdemos.pointcutapi.pointcut;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

// CoustomPointcout 클래스랑 비교해보면 좋다
public class TestStaticPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return method.getName().startsWith("update");
    }
}