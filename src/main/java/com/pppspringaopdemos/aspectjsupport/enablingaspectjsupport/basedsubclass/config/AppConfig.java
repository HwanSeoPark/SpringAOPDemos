package com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedsubclass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedsubclass.aspect.MyAspect;
import com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedsubclass.service.FooService;

@Configuration
///////////////////////////////////////////////////////////////
// Spring AOP에서 애스펙트가 적용되려면, 타겟 클래스는 반드시 프록시 객체여야 함.
///////////////////////////////////////////////////////////////
/*
 * Spring AOP 프록시 전략:
 *
 * 1. 디폴트 값: @EnableAspectJAutoProxy(proxyTargetClass = false)
 *    - 조건: 타겟 클래스가 하나 이상의 인터페이스를 구현해야 함
 *    - 방식: JDK 다이나믹 프록시 (java.lang.reflect.Proxy)
 *    - 특징: 프록시 객체는 인터페이스만 구현하고 타겟 클래스는 상속하지 않음
 *
 * 2. 설정: @EnableAspectJAutoProxy(proxyTargetClass = true)
 *    - 조건: 타겟 클래스가 인터페이스를 구현하지 않아도 됨
 *    - 방식: CGLIB 프록시 (타겟 클래스를 상속하여 서브클래스 생성)
 *    - 특징: 클래스 기반 프록시 생성. final 클래스나 final 메서드는 프록싱 불가
 */ // 그러나...proxyTargetClass가 디폴트로 false라고 할지라도
    // 강제로 cglib을 사용해서 타겟 클래스를 프록시 클래스로 생성함.
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
	
	@Bean
    public FooService fooService() {
        return new FooService();
    }
	// fooService를 상속한 proxy 클래스가 Bean으로 등록된다
	
    @Bean
    public MyAspect myAspect() {
        return new MyAspect();
    }
}