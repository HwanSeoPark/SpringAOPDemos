package com.pppspringaopdemos.proxyingmechanisms.understandingaopproxies;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class RetryAdvice implements MethodInterceptor {

	// Interceptor는 Advice라고 생각하면 된다
	// Pojo의 foo(), bar()의 메서드의 around advice로 사용됨
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("[RetryAdvice] " + invocation.getMethod().getName() + "() intercepted");
        try {
            return invocation.proceed(); // @Around라 proceed() 호출
        } catch (Exception ex) {
            System.out.println("[RetryAdvice] 첫 번째 시도 실패: " + ex.getMessage());
            System.out.println("[RetryAdvice] 두 번째 시도...");
            return invocation.proceed();
        }
    }
}

