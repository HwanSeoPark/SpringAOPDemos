package com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.aopdemo;

import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.aopdemo.config.AppConfig;
import com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.aopdemo.service.GreetingService;
import com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.aopdemo.service.LoggingService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        GreetingService greetingService = ctx.getBean(GreetingService.class);
        greetingService.sayHello(); // AOP 적용됨

        LoggingService loggingService = ctx.getBean(LoggingService.class);
        loggingService.logMessage(); // AOP 적용됨

        System.out.println("--- Proxy Types ---");
        System.out.println("GreetingService subclass proxy: " + AopUtils.isCglibProxy(greetingService)); 
		System.out.println("GreetingService interface proxy: " + AopUtils.isJdkDynamicProxy(greetingService));
        // GreetingServiceImpl은 인터페이스를 구현한 구현체지만
		// @EnableAspectJAutoProxy(proxyTargetClass = true)가 되면
		// 인터페이스를 구현한 구현체여도 CGLIB으로 proxy를 만들어서 사용
		
		System.out.println("LoggingService subclass proxy: " + AopUtils.isCglibProxy(loggingService)); 
		System.out.println("LoggingService interface proxy: " + AopUtils.isJdkDynamicProxy(loggingService));

    }
}