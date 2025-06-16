package com.pppspringaopdemos.pointcutapi.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// Advice는 aopalliance의 구현체를 통해 구현
// AspectJ스타일로 만든 것들을 다시
// MethodInterceptor를 통해서 어드바이스를 만들어라
public class ExceptionHandlingAdvice implements MethodInterceptor {

	// Advice를 다시 만드는 중
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed(); // JoinPoint의 proceed()메서드
            						     // 다음 어드바이스의 체인 메서드 또는
            							 // 타겟 클래스의 메서드
        } catch (Exception ex) {
            System.out.println("Exception caught in method: " + invocation.getMethod().getName() + ", exception: " + ex.getMessage());
            throw ex;
        }
    }
}