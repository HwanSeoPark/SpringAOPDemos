package com.pppspringaopdemos.adviceapiinspring.advices.introduction;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public LockMixinAdvisor lockMixinAdvisor() {
        return new LockMixinAdvisor();
    }

    @Bean
    public MyTargetClass myTargetClass(LockMixinAdvisor lockMixinAdvisor) {
        ProxyFactory factory = new ProxyFactory(new MyTargetClass()); // 타겟클래스 전달
        factory.addAdvisor(lockMixinAdvisor);
        factory.setProxyTargetClass(true); // CGLIB proxy를 강제 사용
        return (MyTargetClass) factory.getProxy();
    }
}