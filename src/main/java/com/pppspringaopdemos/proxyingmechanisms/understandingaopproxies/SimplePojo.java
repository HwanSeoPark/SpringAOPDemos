package com.pppspringaopdemos.proxyingmechanisms.understandingaopproxies;

public class SimplePojo implements Pojo {


	// 이런 코드를 쓰지마라!!
    @Override
    public void foo() {
        System.out.println("[SimplePojo] foo() 호출됨");
        // 다음 bar 메서드 호출은 프록시가 아닌 this.bar() 이므로, AOP 적용이 안 됨!
        // 이 bar는 RetryAdvice가 적용이 안되버림
        this.bar(); // self-invocation
    }

    // 이 코드의 문제
    // advice가 적용된 메서드를 foo()에서 직접 호출해버림
    // 그러면 foo()에서 호출된 bar()는 프록시가 아니다. AOP가 적용이 안 됨
    @Override
    public void bar() {
        System.out.println("[SimplePojo] bar() 호출됨 (Advice 적용 대상)");
    }
}
