package com.pppspringaopdemos.aspectjsupport.declaringadvice.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.pppspringaopdemos.aspectjsupport.declaringadvice.aop.ExecutionCountingAspect;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.aop.SampleAspect;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.aop.TimingAspect;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.model.MyType;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.service.Sample;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.service.SampleService;

@Configuration
@ComponentScan(basePackages = "com.pppspringaopdemos.aspectjsupport.declaringadvice")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
	
// SampleService가 제너릭 인터페이스 Sample<MyType>를 구현함으로써,
// @EnableAspectJAutoProxy(proxyTargetClass = false)가 적용되지 않아서,
// 직접 ProxyFactoryBean을 생성하려 했으나, 이것도 여의치 않음
// 그러나 (proxyTargetClass = true)로 설정하면 해결됨.
//	  @Bean
//    public SampleService originalSampleService() {
//        return new SampleService();
//    }
//
//    @Bean
//    public ProxyFactoryBean sampleServiceProxy() {
//        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
//        proxyFactoryBean.setTarget(originalSampleService());
//        proxyFactoryBean.setProxyTargetClass(true); // CGLIB 프록시 사용
//        proxyFactoryBean.setInterceptorNames("sampleAspect");
//        return proxyFactoryBean;
//    }
//    
//    @Bean
//    public SampleAspect sampleAspect() {
//        return new SampleAspect();
//    }

    @Bean
    public ExecutionCountingAspect executionCountingAspect() {
        return new ExecutionCountingAspect();
    }

    @Bean
    public TimingAspect timingAspect() {
        return new TimingAspect();
    }

}