package com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.aopdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.aopdemo.service.GreetingService;
import com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.aopdemo.service.GreetingServiceImpl;
import com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.aopdemo.service.LoggingService;

@Configuration
@ComponentScan("com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.aopdemo")
@EnableAspectJAutoProxy(proxyTargetClass = true) // 인터페이스 있어도 CGLIB으로 처리
public class AppConfig {

    @Bean
    public GreetingService greetingService() {
        return new GreetingServiceImpl();
    }

    @Bean
    public LoggingService loggingService() {
        return new LoggingService();
    }
}