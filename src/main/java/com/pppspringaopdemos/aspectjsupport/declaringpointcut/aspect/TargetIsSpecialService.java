package com.pppspringaopdemos.aspectjsupport.declaringpointcut.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TargetIsSpecialService {

	// target 디지그네이터를 사용하여 실제 타겟 객체의 타입에 따라 매칭
    @Pointcut("target(com.pppspringaopdemos.aspectjsupport.declaringpointcut.service.SpecialService)")
    private void targetIsSpecialService() {}
 
    // 타겟 객체가 SpecialService일 때, 타겟 클래스와 호출되는 메서드 이름을 로그로 출력합니다.
    @Before("targetIsSpecialService()")
    public void logWhenTargetIsSpecialService(JoinPoint joinPoint) {
        System.out.println("Logging when target is of type SpecialService");
        System.out.println("Target class: " + joinPoint.getTarget().getClass().getName());
        System.out.println("Method being called: " + joinPoint.getSignature().getName());
    }

    


}
