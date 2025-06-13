package com.pppspringaopdemos.aspectjsupport.declaringadvice.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.pppspringaopdemos.aspectjsupport.declaringadvice.model.Account;

@Aspect
@Component
public class AccountValidationAspect {

    // 특정 포인트컷을 정의: 
	// (..): 메서드의 파라미터 개수와 타입에 상관없이 일치. 
	// 즉, 파라미터가 0개일 수도 있고, 여러 개일 수도 있음.
	// Account 인스턴스를 첫 번째 아규먼트[args(account,..)]로 받는 메서드 실행
	// arg(..): 단순히 아규먼트가 있든 없든 상관없이 포인트컷을 적용
	// args(account, ..) : 특정 아규먼트의 값에 접근하거나 수정하려는 목적
    @Pointcut("execution(* com.pppspringaopdemos.aspectjsupport.declaringadvice.dao.*.*(..)) && args(account,..)")
    private void accountDataAccessOperation(Account account) {}

    // 해당 포인트컷에 매칭되는 메서드 실행 전에 검증 수행
    @Before("accountDataAccessOperation(account)")
    public void validateAccount(Account account) {
        // 간단한 검증 예제: 잔액이 음수인지 확인
        if (account.getBalance() < 0) {
            throw new IllegalArgumentException("Account balance cannot be negative");
        }
        System.out.println("Account validation passed for: " + account);
    }
}