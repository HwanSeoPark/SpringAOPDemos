package com.pppspringaopdemos.aspectjsupport.combindedpointcuts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.pppspringaopdemos.aspectjsupport.combindedpointcuts.aspect.LoggingAspect;
import com.pppspringaopdemos.aspectjsupport.combindedpointcuts.pointcuts.CommonPointcuts;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.pppspringaopdemos.aspectjsupport.combindedpointcuts")
public class AppConfig {

//    @Bean
//    public CommonPointcuts commonPointcuts() {
//        return new CommonPointcuts();
//    }

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}