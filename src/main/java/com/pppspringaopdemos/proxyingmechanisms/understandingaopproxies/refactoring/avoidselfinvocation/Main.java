package com.pppspringaopdemos.proxyingmechanisms.understandingaopproxies.refactoring.avoidselfinvocation;

import org.springframework.aop.framework.ProxyFactory;

import com.pppspringaopdemos.proxyingmechanisms.understandingaopproxies.RetryAdvice;


public class Main {
    public static void main(String[] args) {
    	// 1. barProxy 생성 (프록시)
        BarDelegateImpl barTarget = new BarDelegateImpl();
        ProxyFactory barFactory = new ProxyFactory(barTarget);
        barFactory.addInterface(BarDelegate.class);
        barFactory.addAdvice(new RetryAdvice());
        BarDelegate barProxy = (BarDelegate) barFactory.getProxy();

        // 2. simplePojoProxy 생성 (프록시)
        SimplePojo simplePojo = new SimplePojo(barProxy); // 실제 객체
        ProxyFactory pojoFactory = new ProxyFactory(simplePojo);
        pojoFactory.addInterface(Pojo.class);
        pojoFactory.addAdvice(new RetryAdvice()); // ✅ foo()에도 Advice 적용됨
        Pojo pojoProxy = (Pojo) pojoFactory.getProxy();

        // 3. 호출
        System.out.println("==== pojo.foo() 호출 시작 ====");
        pojoProxy.foo();  // ✅ foo() → delegate.bar() → 모두 Advice 적용
    }
}
