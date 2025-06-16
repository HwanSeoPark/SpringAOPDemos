package com.pppspringaopdemos.pointcutapi.config;

import java.util.List;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.pppspringaopdemos.pointcutapi.advice.ExceptionHandlingAdvice;
import com.pppspringaopdemos.pointcutapi.advice.ExecutionTimeAdvice;
import com.pppspringaopdemos.pointcutapi.advice.LoggingAdvice;
import com.pppspringaopdemos.pointcutapi.pointcut.CustomPointcut;
import com.pppspringaopdemos.pointcutapi.service.AnotherService;
import com.pppspringaopdemos.pointcutapi.service.MyService;
import com.pppspringaopdemos.usingtheproxyfactorybeantocreateaopproxies.jbproperties.service.SimpleService;

@Configuration
public class AppConfig {

    @Bean
    public Pointcut customPointcut() {
        return new CustomPointcut();
    }

    @Bean
    public Pointcut aspectJPointcut() {
    	// AspectJExpressionPointcut은 스프링 프레임 워크에서 지원
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut(); 
        pointcut.setExpression("execution(* com.pppspringaopdemos.pointcutapi.service.MyService.myMethod(..))");
        // 이 pointcut 스타일은 AspectJ 스타일이다
        return pointcut;
    }

    // @Qualifier 어노테이션은 auto-wire 시 후보 빈에 대한 한정자로 필드나 파라미터에 사용될 수 있습니다. 
    // 또한 다른 사용자 정의 어노테이션에 어노테이션을 달고, 그런 다음 한정자로 사용할 수 있습니다.
    // Advisor = Pointcut + Advice
    @Lazy
    @Bean
    public DefaultPointcutAdvisor loggingAdvisor(@Qualifier("customPointcut") Pointcut pointcut) {
    											// customPointcut이라는 이름의 pointcut전달
        return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
        						     // 첫 아규먼트로 pointcut
        							 // 두번째 아규먼트로 advice
    }

    @Lazy
    @Bean
    public DefaultPointcutAdvisor executionTimeAdvisor(@Qualifier("customPointcut") Pointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new ExecutionTimeAdvice());
    }

    @Lazy
    @Bean
    public DefaultPointcutAdvisor exceptionHandlingAdvisor(@Qualifier("customPointcut") Pointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new ExceptionHandlingAdvice());
    }

    @Lazy
    @Bean
    public DefaultPointcutAdvisor aspectJLoggingAdvisor(@Qualifier("aspectJPointcut") Pointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
    }

    @Lazy
    @Bean
    public ProxyFactoryBean anotherServiceProxy(
            AnotherService anotherService,
            @Qualifier("loggingAdvisor") DefaultPointcutAdvisor loggingAdvisor,
            @Qualifier("executionTimeAdvisor") DefaultPointcutAdvisor executionTimeAdvisor,
            @Qualifier("exceptionHandlingAdvisor") DefaultPointcutAdvisor exceptionHandlingAdvisor) {

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(anotherService);
        proxyFactoryBean.setInterceptorNames("loggingAdvisor", "executionTimeAdvisor", "exceptionHandlingAdvisor");
        return proxyFactoryBean;
    }

    @Bean
    public MyService myService() {
        return new MyService();
    }

    @Bean
    public AnotherService anotherService() {
        return new AnotherService();
    }

    @Lazy
    @Bean
    // ProxyFactoryBean는 ProxyFactory를 만드는 Bean
    // PorxyFactory와는 다름...
    public ProxyFactoryBean myServiceProxy(
            MyService myService,
            @Qualifier("loggingAdvisor") DefaultPointcutAdvisor loggingAdvisor,
            @Qualifier("executionTimeAdvisor") DefaultPointcutAdvisor executionTimeAdvisor,
            @Qualifier("exceptionHandlingAdvisor") DefaultPointcutAdvisor exceptionHandlingAdvisor,
            @Qualifier("aspectJLoggingAdvisor") DefaultPointcutAdvisor aspectJLoggingAdvisor) {

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(myService);
        proxyFactoryBean.setInterceptorNames("loggingAdvisor", 
        									"executionTimeAdvisor",
        									"exceptionHandlingAdvisor", 
        									"aspectJLoggingAdvisor");
        return proxyFactoryBean;
    }
    
    // 굳이 이렇게 작성할 필요없다
    // 클라이언트에서 getBean만 해도 자동으로 프록시 만들어줌
//    @Bean
//    public MyService proxyMyService() throws BeansException, ClassNotFoundException {
//        return (MyService) myServiceProxy(myService()).getObject();
//    }
    
}