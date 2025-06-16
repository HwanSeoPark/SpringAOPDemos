package com.pppspringaopdemos.adviceapiinspring.config;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pppspringaopdemos.adviceapiinspring.advices.CountingAfterReturningAdvice;
import com.pppspringaopdemos.adviceapiinspring.advices.CountingBeforeAdvice;
import com.pppspringaopdemos.adviceapiinspring.advices.DebugInterceptor;
import com.pppspringaopdemos.adviceapiinspring.advices.SimpleThrowsAdvice;
import com.pppspringaopdemos.adviceapiinspring.service.SimpleService;

@Configuration
public class AopConfig {

    @Bean
    public SimpleService simpleService() {
        return new SimpleService();
    }

    @Bean
    public DebugInterceptor debugInterceptor() {
        return new DebugInterceptor();
    }

    @Bean
    public CountingBeforeAdvice countingBeforeAdvice() {
        return new CountingBeforeAdvice();
    }

    @Bean
    public CountingAfterReturningAdvice countingAfterReturningAdvice() {
        return new CountingAfterReturningAdvice();
    }

    @Bean
    public SimpleThrowsAdvice simpleThrowsAdvice() {
        return new SimpleThrowsAdvice();
    }

    @Bean
    public ProxyFactoryBean proxyFactoryBean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(simpleService()); // 시지립으로 만들겠다.

        /*
          public DefaultPointcutAdvisor(Advice advice) {
			 this(Pointcut.TRUE, advice);
		  }
         */
        // DefaultPointcutAdvisor(Advice advice):
        // :위 생성자는 모든 메서드와 일치하는 DefaultPointcutAdvisor를 생성합니다.
        // Pointcut TRUE = TruePointcut.INSTANCE; // 항상 일치하는 정식 Pointcut 인스턴스입니다.
        //  Pointcut.TRUE will be used as Pointcut
        // 현재 SimpleService의 모든 메서드에게 다음과 같은 인터셉터 체인이 지원된다.
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(countingBeforeAdvice()));
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(debugInterceptor()));
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(countingAfterReturningAdvice()));
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(simpleThrowsAdvice()));

        return proxyFactoryBean;
    }
}
