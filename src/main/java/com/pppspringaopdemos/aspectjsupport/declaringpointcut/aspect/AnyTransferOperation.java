package com.pppspringaopdemos.aspectjsupport.declaringpointcut.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnyTransferOperation {

	// 특정 메서드 이름을 매칭하는 포인트컷	
	    // * 은 리턴값이 어떤게 오든 상관없다
	//("execution(* transfer(..))")의 의미는
	// 이름이 transfer이라는 메서드면 리턴값이 어떤게 오든
	// 파라미터정의가 몇개이든 상관없다는 의미
	@Pointcut("execution(* transfer(..))") // 포인트컷 expression
	private void anyTransferOperation() {} // 포인트컷 시크니쳐
	// anyTransferOperation가 현재 Pointcut의 이름

	// 어드바이스 정의
    // transfer 메서드가 실행되기 전에 메서드 이름, 전달된 아규먼트, 타겟 클래스의 정보를 로그로 출력합니다.
    @Before("anyTransferOperation()")
    public void logBeforeTransfer(JoinPoint joinPoint) {
        System.out.println("Logging before transfer operation");
        System.out.println("Method: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("Target class: " + joinPoint.getTarget().getClass().getName());
    }


}
