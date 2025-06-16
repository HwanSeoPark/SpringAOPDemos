package com.pppspringaopdemos.pointcutapi.config;

import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.pppspringaopdemos.pointcutapi.advice.ExceptionHandlingAdvice;
import com.pppspringaopdemos.pointcutapi.advice.ExecutionTimeAdvice;
import com.pppspringaopdemos.pointcutapi.advice.LoggingAdvice;
import com.pppspringaopdemos.pointcutapi.pointcut.CustomPointcut;
import com.pppspringaopdemos.pointcutapi.service.AnotherService;
import com.pppspringaopdemos.pointcutapi.service.MyService;

@Configuration
@EnableAspectJAutoProxy
public class AppConfigForEnableAspectJAutoProxy {

    @Bean
    public Pointcut customPointcut() {
        return new CustomPointcut();
    }

    @Bean
    public Pointcut aspectJPointcut() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.pppspringaopdemos.pointcutapi.service.MyService.myMethod(..))");
        return pointcut;
    }

    @Bean
    public DefaultPointcutAdvisor loggingAdvisor(@Qualifier("customPointcut") Pointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
    }

    @Bean
    public DefaultPointcutAdvisor executionTimeAdvisor(@Qualifier("customPointcut") Pointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new ExecutionTimeAdvice());
    }

    @Bean
    public DefaultPointcutAdvisor exceptionHandlingAdvisor(@Qualifier("customPointcut") Pointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new ExceptionHandlingAdvice());
    }

    @Bean
    public DefaultPointcutAdvisor aspectJLoggingAdvisor(@Qualifier("aspectJPointcut") Pointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
    }

    @Bean
    public MyService myService() {
        return new MyService();
    }

    @Bean
    public AnotherService anotherService() {
        return new AnotherService();
    }

}