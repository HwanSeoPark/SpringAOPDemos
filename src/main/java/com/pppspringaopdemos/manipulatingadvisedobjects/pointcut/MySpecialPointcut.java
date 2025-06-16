package com.pppspringaopdemos.manipulatingadvisedobjects.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class MySpecialPointcut extends StaticMethodMatcherPointcut {
    // 특정 클래스를 지정하는 기능이 없다
	
	@Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "performOperation".equals(method.getName());
    }
}