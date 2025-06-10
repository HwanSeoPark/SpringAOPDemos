package com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedsubclass.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {

	// 이 pointcut 으로 FooService가 Target이 됨
	// @Before : 타켓 메서드 호출전에 호출한다
    @Before("execution(* com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedsubclass.service.FooService+.*(..))")
    public void advice() {
        System.out.println("MyAspect advice");
    }
}