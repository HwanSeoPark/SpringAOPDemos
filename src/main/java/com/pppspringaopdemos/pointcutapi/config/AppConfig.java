package com.pppspringaopdemos.pointcutapi.config;

import java.util.List;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;

import com.pppspringaopdemos.pointcutapi.advice.AuditTrailAdvice;
import com.pppspringaopdemos.pointcutapi.advice.ExceptionHandlingAdvice;
import com.pppspringaopdemos.pointcutapi.advice.ExecutionTimeAdvice;
import com.pppspringaopdemos.pointcutapi.advice.LoggingAdvice;
import com.pppspringaopdemos.pointcutapi.advice.MethodAccessLoggingAdvice;
import com.pppspringaopdemos.pointcutapi.advice.MethodCallCountingAdvice;
import com.pppspringaopdemos.pointcutapi.advice.NotificationLoggingAdvice;
import com.pppspringaopdemos.pointcutapi.advice.UpdateLoggingAdvice;
import com.pppspringaopdemos.pointcutapi.pointcut.CustomPointcut;
import com.pppspringaopdemos.pointcutapi.pointcut.MyPointcutAdvisor;
import com.pppspringaopdemos.pointcutapi.pointcut.TestStaticPointcut;
import com.pppspringaopdemos.pointcutapi.service.AnotherService;
import com.pppspringaopdemos.pointcutapi.service.MyService;
import com.pppspringaopdemos.pointcutapi.service.NotificationService;
import com.pppspringaopdemos.pointcutapi.service.OrderService;
import com.pppspringaopdemos.pointcutapi.service.ProductService;
import com.pppspringaopdemos.pointcutapi.service.SimpleService;
import com.pppspringaopdemos.pointcutapi.service.TaskCaller;
import com.pppspringaopdemos.pointcutapi.service.UserAccountService;
import com.pppspringaopdemos.pointcutapi.service.Auditable;

@Configuration
//@EnableAspectJAutoProxy
public class AppConfig {
	
	@Bean
    public SimpleService simpleService() {
        return new SimpleService();
    }
	
	@Bean
	public TaskCaller taskCaller(@Qualifier("simpleServiceProxy") SimpleService simpleService) {
	    return new TaskCaller(simpleService);
	}	

    @Bean
    public Pointcut customPointcut() {
        return new CustomPointcut();
    }

    @Bean
    public Pointcut aspectJPointcut() {
    	// @Poi
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.pppspringaopdemos.pointcutapi.service.MyService.myMethod(..))");
        return pointcut;
    } 
    
    @Bean
    public Advisor auditableAdvisor() {
        return new DefaultPointcutAdvisor(
        		//Attribute-driven Pointcuts
        		// 클래스에 붙은 어노테이션은 무시(null 아규먼트 전달)
        		// 메서드에 붙은 @Auditable만 매칭하고 싶을 때(Auditable.class 아규먼트 전달)
                new AnnotationMatchingPointcut(null, Auditable.class),
                new AuditTrailAdvice()
        );
    }
    
    @Bean
    public OrderService targetOrderService() {
        return new OrderService();
    }

    @Bean
    public ProxyFactoryBean orderServiceProxy() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(targetOrderService());
        proxyFactoryBean.setInterceptorNames("auditableAdvisor");// 빈으로 등록된 어드바이저
        return proxyFactoryBean;
    }
    
    @Bean
    public Pointcut controlFlowPointcut() {
    	// 지정한 클래스(1st arg : TaskCaller.class)의 
    	// 특정 메서드(2nd arg : callTask)가 실행 중일 때, 
    	// 이 메서드 실행 흐름 안에서 호출된 메서드만 매칭시킨다
    	// callTask에서 SimpleService.performTask()를 호출.
    	// 이 때, performTask에 LoggingAdvice가 적용됨
    	// : 즉, callTask안에서 performTask가 호출될 경우만,
    	//   performTask에게 LoggingAdvice가 적용됨
        return new ControlFlowPointcut(TaskCaller.class, "callTask");
    }
    
    // 여기서 contrlFlowPoiutcut의 Advice를 지정하고 있음
    @Lazy
    @Bean
    public DefaultPointcutAdvisor controlFlowLoggingAdvisor(
            @Qualifier("controlFlowPointcut") Pointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
    }
    
    @Lazy
    @Bean
    public ProxyFactoryBean simpleServiceProxy(
            SimpleService simpleService,
            // DefaultPointcutAdvisor 빈이 많아서 Qualifier을 써서 지정
            @Qualifier("controlFlowLoggingAdvisor") DefaultPointcutAdvisor controlFlowLoggingAdvisor) {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(simpleService);
        proxyFactoryBean.setInterceptorNames("controlFlowLoggingAdvisor");
        return proxyFactoryBean;
    }

    // @Qualifier 어노테이션은 auto-wire 시 후보 빈에 대한 한정자로 필드나 파라미터에 사용될 수 있습니다. 
    // 또한 다른 사용자 정의 어노테이션에 어노테이션을 달고, 그런 다음 한정자로 사용할 수 있습니다.
    // Advisor = pointcut + advice
    @Lazy
    @Bean
    public DefaultPointcutAdvisor 
    	loggingAdvisor(@Qualifier("customPointcut") Pointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
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

    
    
    @Bean
    public MyPointcutAdvisor retryAdvisor() {
        return new MyPointcutAdvisor();
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
    public ProxyFactoryBean myServiceProxy(
            MyService myService/*,
            @Qualifier("loggingAdvisor") DefaultPointcutAdvisor loggingAdvisor,
            @Qualifier("executionTimeAdvisor") DefaultPointcutAdvisor executionTimeAdvisor,
            @Qualifier("exceptionHandlingAdvisor") DefaultPointcutAdvisor exceptionHandlingAdvisor,
            @Qualifier("aspectJLoggingAdvisor") DefaultPointcutAdvisor aspectJLoggingAdvisor*/) {

    	// ProxyFactory와는 다름...
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(myService);
        proxyFactoryBean.setInterceptorNames("loggingAdvisor", 
        		"executionTimeAdvisor", 
        		"exceptionHandlingAdvisor", 
        		"aspectJLoggingAdvisor");
        return proxyFactoryBean;
    }
    
//    @Bean
//    public MyService proxyMyService() throws BeansException, ClassNotFoundException {
//        return (MyService) myServiceProxy(myService()).getObject();
//    }

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
    public MethodCallCountingAdvice methodCallCountingAdvice() {
        return new MethodCallCountingAdvice();
    }
    
    @Bean
    public MethodAccessLoggingAdvice methodAccessLoggingAdvice() {
        return new MethodAccessLoggingAdvice();
    }
    
    // 정규 표현식
    // Pointcut
    @Bean
    public JdkRegexpMethodPointcut jdkRegexpMethodPointcut() {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        // 이 패턴은 "get"이라는 문자열을 가지는 모든 메서드에 적용됩니다.
        pointcut.setPatterns(".*get.*");  
        return pointcut;
    }    
    
    // @Aspect 어노테이션이 적용된 클래스 : 포인트컷 + 어드바이스 -> AspectJ 기능
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        return new DefaultPointcutAdvisor(jdkRegexpMethodPointcut(), methodAccessLoggingAdvice());
    }
    
    @Bean
    public ProductService targetProductService() {
        return new ProductService();
    }
    @Bean
    public ProxyFactoryBean productServiceProxy() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(targetProductService()); // 타겟클래스로 ProductService만 지정
        proxyFactoryBean.setInterceptorNames("defaultPointcutAdvisor"); // 어드바이저 연결
        return proxyFactoryBean;
    }
    
    
    @Bean
    public TestStaticPointcut testStaticPointcut() {
        return new TestStaticPointcut();
    }

    @Bean
    public UpdateLoggingAdvice updateLoggingAdvice() {
        return new UpdateLoggingAdvice();
    }

    @Bean
    public DefaultPointcutAdvisor updateAdvisor() {
        return new DefaultPointcutAdvisor(testStaticPointcut(), updateLoggingAdvice());
    }
    
    @Bean
    public UserAccountService targetUserAccountService() {
        return new UserAccountService();
    }

    @Bean
    public ProxyFactoryBean userAccountServiceProxy() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(targetUserAccountService());
        proxyFactoryBean.setInterceptorNames("updateAdvisor"); // StaticPointcut + Advice 연결됨
        return proxyFactoryBean;
    }


    @Bean
    public NotificationService targetNotificationService() {
        return new NotificationService();
    }

    @Bean
    public NotificationLoggingAdvice notificationLoggingAdvice() {
        return new NotificationLoggingAdvice();
    }

    @Bean
    public RegexpMethodPointcutAdvisor settersAndAbsquatulateAdvisor() {
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        advisor.setAdvice(notificationLoggingAdvice());
        // 두 가지 패턴을 설정하여, 
        // 메서드 이름에 "set"이나 "absquatulate"라는 단어가 포함된 메서드를 포인트컷으로 지정하는 것입니다. 
        // 이 포인트컷이 적용된 메서드가 호출될 때마다, 해당 어드바이스가 실행됩니다.
        // 예를 들어, setName과 absquatulate 메서드는 이 패턴과 일치하므로, 
        // 이 메서드들이 호출될 때 어드바이스가 적용됩니다. 
        // 반면, 이 패턴과 일치하지 않는 메서드(예: performTask)에는 어드바이스가 적용되지 않습니다.
        advisor.setPatterns(".*set.*", ".*absquatulate.*");
        return advisor;
    }

    @Bean
    public ProxyFactoryBean notificationServiceProxy() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(targetNotificationService());
        proxyFactoryBean.addAdvisor(settersAndAbsquatulateAdvisor());
        return proxyFactoryBean;
    }

 
}