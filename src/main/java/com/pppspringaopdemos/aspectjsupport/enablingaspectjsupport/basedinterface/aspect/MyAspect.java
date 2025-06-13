package com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedinterface.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {
	// 마지막의 FooService+의 + 는 구현 or 상속한 애들도 다 가능하다라는 의미
	// 다음 Pointcut expression은 AspectJ에서 채용함.
	// inline Pointcut : 재사용은 안됨
    @Before("execution(* com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedinterface.service.FooService+.*(..))")
    public void advice() {
        System.out.println("MyAspect advice");
    }
}