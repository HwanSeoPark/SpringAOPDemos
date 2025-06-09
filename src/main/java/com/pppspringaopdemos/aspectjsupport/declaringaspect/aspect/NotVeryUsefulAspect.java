package com.pppspringaopdemos.aspectjsupport.declaringaspect.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotVeryUsefulAspect {

	// @Before 어노테이션의 value 속성의 값으로 다음과 같이 Pointcut expresson을 설정할 수 있음.
    @Before("execution(* com.pppspringaopdemos.aspectjsupport.declaringaspect.service.MyService.*(..))")
    public void beforeMethod() {
        System.out.println("Aspect is triggered before method execution");
    }
}