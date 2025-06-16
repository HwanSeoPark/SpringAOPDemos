package com.pppspringaopdemos.proxyingmechanisms.understandingaopproxies.refactoring.injectselfreference;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.pppspringaopdemos.proxyingmechanisms.understandingaopproxies.Pojo;

public class Main {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
               
        // ProxyFactoryBean에서 생성된 프록시를 가져옵니다.
        Pojo proxy = (Pojo) ctx.getBean(Pojo.class);

        System.out.println("==== foo() 호출 ====");
        proxy.foo(); // foo → self.bar() → Advice 적용됨
    }
}
