package com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

	
    //@Before("execution(* com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.aopdemo.service.*(..))")
    @Before("execution(* com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.aopdemo.service..*(..))")
    public void beforeAdvice() {
        System.out.println(">>> [AOP] Method is about to be called");
    }
}