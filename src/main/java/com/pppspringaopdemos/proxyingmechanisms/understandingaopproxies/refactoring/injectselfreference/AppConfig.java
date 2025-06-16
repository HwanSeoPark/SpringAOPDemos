package com.pppspringaopdemos.proxyingmechanisms.understandingaopproxies.refactoring.injectselfreference;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.pppspringaopdemos.proxyingmechanisms.understandingaopproxies.Pojo;
import com.pppspringaopdemos.proxyingmechanisms.understandingaopproxies.RetryAdvice;
import com.pppspringaopdemos.usingtheproxyfactorybeantocreateaopproxies.jbproperties.service.SimpleService;

@Configuration
public class AppConfig {

    @Bean
    public RetryAdvice retryAdvice() {
        return new RetryAdvice();
    }

    // 실제 객체 (프록시 대상)
    @Bean
    public SimplePojo simplePojo() {
        return new SimplePojo();
    }

    // 프록시 등록
    @Bean
    @Primary // SimplePojo도 Pojo인터페이스 구현체고
    		 // 여기의 프록시클래스도 Pojo인터페이스의 구현체이기 때문에
             // @Primary 적용해서 우선 순위 적용
    public Pojo pojo(SimplePojo target) {
        ProxyFactory factory = new ProxyFactory(target);
        factory.setInterfaces(Pojo.class);
        factory.addAdvice(retryAdvice());
        factory.setProxyTargetClass(false); // JDK 동적 프록시
        return (Pojo) factory.getProxy();
    }
}
