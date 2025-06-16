package com.pppspringaopdemos.pointcutapi.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class RetryAdvice implements MethodInterceptor {

    private static final int MAX_RETRIES = 3;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        int attempts = 0;
        while (true) {
            try {
                System.out.println("[RetryAdvice] 시도 #" + (attempts + 1));
                return invocation.proceed();
            } catch (Exception e) {
                attempts++;
                System.out.println("[RetryAdvice] 예외 발생: " + e.getMessage());
                if (attempts >= MAX_RETRIES) {
                    System.out.println("[RetryAdvice] 최대 재시도 회수 도달. 예외 던짐.");
                    throw e;
                }
                System.out.println("[RetryAdvice] 재시도 중...");
            }
        }
    }
}
