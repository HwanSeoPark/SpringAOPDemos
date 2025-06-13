package com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedinterface.confing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedinterface.aspect.MyAspect;
import com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedinterface.service.FooService;
import com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedinterface.service.FooServiceImpl;
import com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedinterface.service.HelloService;

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
 */ 
@EnableAspectJAutoProxy
public class AppConfig {
	
	@Bean
    public FooService fooService() {
        return new FooServiceImpl();
    }
	// 여기도 보면 fooService()를 빈으로 등록하는걸 볼수있음

	@Bean
	public HelloService helloService() {
		// fppServoce() 호출하면 어떻게 되나요?
		// : 바로 호출 안됨!! 스프링 IoC 컨테이너에 있는 fooService()의 빈을 전달해줌
		return new HelloService(fooService()); // 
	}
	
    @Bean
    public MyAspect myAspect() {
        return new MyAspect();
    }
}