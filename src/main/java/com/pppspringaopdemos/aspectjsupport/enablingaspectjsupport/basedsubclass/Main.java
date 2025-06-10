package com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedsubclass;

import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedsubclass.config.AppConfig;
import com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedsubclass.service.FooService;


public class Main {

	public static void main(String[] args) {
		ApplicationContext context = 
        		new AnnotationConfigApplicationContext(AppConfig.class);

		FooService fooService = context.getBean(FooService.class);
		
		System.out.println(AopUtils.isCglibProxy(fooService)); // → true
		System.out.println(AopUtils.isJdkDynamicProxy(fooService)); // → false
		// CGLIB으로 만든 Proxy인지 
		// jdk 다이나믹 Proxy로 만든 Proxy인지 구분해주는 메서드(유틸리티 에서 지원)
		
		fooService.helloFoo();
		// FooService를 상속하여 오버라이드한 프록시 클래스의 helloFoo()메서드도 있고(하이딩)
		// 부모 클래스(FooService)의 helloFoo() 메서드도 있다
	}

}
