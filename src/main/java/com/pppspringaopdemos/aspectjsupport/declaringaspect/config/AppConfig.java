package com.pppspringaopdemos.aspectjsupport.declaringaspect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.pppspringaopdemos.aspectjsupport.declaringaspect.aspect.NotVeryUsefulAspect;
import com.pppspringaopdemos.aspectjsupport.declaringaspect.service.MyService;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.pppspringaopdemos.aspectjsupport.declaringaspect")
public class AppConfig {

//    @Bean
//    public NotVeryUsefulAspect notVeryUsefulAspect() {
//        return new NotVeryUsefulAspect();
//    }

    @Bean
    public MyService myService() {
        return new MyService();
    }
}