package com.pppspringaopdemos.proxyingmechanisms.understandingaopproxies.refactoring.injectselfreference;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.pppspringaopdemos.proxyingmechanisms.understandingaopproxies.Pojo;

public class SimplePojo implements Pojo {

    @Autowired
    @Lazy // 🔁 지연 참조: 아직 빈 생성이 완료되지 않은 자기 자신에 대한 프록시를 주입받기 위해 필요
    private Pojo self; // self dependency injection(필드에 의한...) : 사용하지 마세요!!!

    @Override
    public void foo() {
        System.out.println("[SimplePojo] foo() 호출됨");
        self.bar(); // ✅ 프록시를 통한 메서드 호출 : self invocation을 회피할수있다
    }

    @Override
    public void bar() {
        System.out.println("[SimplePojo] bar() 호출됨 (Advice 대상)");
    }
}