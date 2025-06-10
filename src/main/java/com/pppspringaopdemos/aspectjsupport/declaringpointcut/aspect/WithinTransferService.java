package com.pppspringaopdemos.aspectjsupport.declaringpointcut.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WithinTransferService {

	// 특정 클래스 내의 모든 메서드를 매칭하는 포인트컷
    @Pointcut("within(com.pppspringaopdemos.aspectjsupport.declaringpointcut.service.TransferService)")
    private void withinTransferService() {}
	
	// TransferService 클래스 내의 모든 메서드 실행 전에 메서드 이름과 타겟 객체의 정보를 로그로 출력합니다.
    @Before("withinTransferService()")
    public void logBeforeWithinService(JoinPoint joinPoint) {
        System.out.println("Logging before any method in TransferService");
        System.out.println("Method: " + joinPoint.getSignature().getName());
        System.out.println("Target object: " + joinPoint.getTarget());
    }

}
