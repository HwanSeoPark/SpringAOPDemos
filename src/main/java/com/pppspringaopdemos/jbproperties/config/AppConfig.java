package com.pppspringaopdemos.jbproperties.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pppspringaopdemos.jbproperties.advice.LoggingBeforeAdvice;
import com.pppspringaopdemos.jbproperties.service.SimpleService;
import com.pppspringaopdemos.jbproperties.service.SimpleServiceImpl;

@Configuration
public class AppConfig {

    // 타겟 빈 정의
    @Bean
    public SimpleService simpleService() {
        return new SimpleServiceImpl();
    }

    // 어드바이스 빈 정의
    @Bean
    public LoggingBeforeAdvice loggingBeforeAdvice() {
        return new LoggingBeforeAdvice();
    }

    // 어드바이저 빈 정의
    // AspectJ 스타일로 aspect[모듈:클래스]를 만들지 않고 ,
    // Advisor = pointcut + advice
    @Bean
    public DefaultPointcutAdvisor loggingAdvisor() {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("doSomething");

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(loggingBeforeAdvice());
        return advisor;
    }

    // ProxyFactoryBean 정의
    // 이걸 이용해 특정 proxy를 만들겠다
    // 빈을 만들지는 않고 등록만 함
    @Bean
    public ProxyFactoryBean proxyFactoryBean() throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(simpleService());

        // CGLIB 프록시를 강제로 사용하도록 설정
        proxyFactoryBean.setProxyTargetClass(true);

        // 공격적인 최적화를 적용 (CGLIB 프록시만 해당)
        // 트레이드 오프가 발생 : 최적화를 적용, 웹 어플리케이션의 성능 하락이 됨.
        /*
         Enhancer enhancer = new Enhancer();
         enhancer.setUseCache(false);
         enhancer.setUseFactory(true);
      
         */
        proxyFactoryBean.setOptimize(true);

        // 프록시 구성을 동결하여 더 이상의 변경을 방지
        proxyFactoryBean.setFrozen(false);

        // 현재 프록시를 AopContext를 통해 노출
        /*
         ProxyConfig#setExposeProxy(boolean exposeProxy) 메서드는
         Spring AOP에서 프록시를 현재 스레드의 ThreadLocal[데이터 저장, 보관 : 스레드의 라이프사이클과 함께]에 
         노출할지 여부를 설정하는 메서드입니다.
         이를 설정함으로써 프록시 자신을 AopContext.currentProxy()를 통해 코드에서 접근할 수 있게 됩니다
         */
        proxyFactoryBean.setExposeProxy(true);

        // 특정 인터페이스를 프록시로 설정
        /*
         이 메서드를 통해 프록시 객체가 구현할 인터페이스를 명시적으로 지정할 수 있습니다.
         지정된 인터페이스들이 있을 경우, Spring AOP는 JDK 다이나믹 프록시를 생성합니다.
         인터페이스가 지정되지 않으면, Spring은 CGLIB 프록시를 생성합니다(즉, 대상 클래스 자체를 서브클래싱하여 프록시를 만듦)
         */
        proxyFactoryBean.setProxyInterfaces(new Class<?>[]{SimpleService.class});

        // 적용할 인터셉터(어드바이저) 설정
        // 인터셉터 = 어드바이저랑 등가
        proxyFactoryBean.setInterceptorNames("loggingAdvisor");

        // singleton 속성 설정
        proxyFactoryBean.setSingleton(true);
        
        return proxyFactoryBean;
    }

    // 실제 사용할 서비스 빈을 정의합니다.
    @Bean
    public SimpleService proxySimpleService() throws BeansException, ClassNotFoundException {
        return (SimpleService) proxyFactoryBean().getObject(); // getObject()로 프록시 생성
    }
}