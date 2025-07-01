package com.pppspringaopdemos.usingtheautoproxyfacility.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.interceptor.SimpleTraceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionAttributeSourceAdvisor;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.pppspringaopdemos.usingtheautoproxyfacility.transaction.SimpleTransactionManager;
import com.pppspringaopdemos.usingtheautoproxyfacility.bean.BusinessObject1;
import com.pppspringaopdemos.usingtheautoproxyfacility.bean.BusinessObject2;
import com.pppspringaopdemos.usingtheautoproxyfacility.bean.MyBean;

@Configuration
@EnableTransactionManagement
//만약 BusinessObject1/2가 특정 인터페이스를 구현하지 않는다면, 
//proxyTargetClass 속성을 true로 설정해야 합니다
//@EnableTransactionManagement(proxyTargetClass = true)
public class AppConfig {

	// BeanNameAutoProxyCreator 정의 (우선순위 설정)
    @Bean
    @Order(1)
    public static BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator proxyCreator = new BeanNameAutoProxyCreator();
        proxyCreator.setBeanNames("jdk*", "onlyJdk");
        proxyCreator.setInterceptorNames("myInterceptor");
        return proxyCreator;
    }

    // SimpleTraceInterceptor 정의
    // SimpleTraceInterceptor 스프링 프레임워크가 지원하는 어드바이스
    @Bean
    public SimpleTraceInterceptor myInterceptor() {
        return new SimpleTraceInterceptor();
    }

    // 예제 빈 정의 (BeanNameAutoProxyCreator로 자동 프록시 생성)
    @Bean
    public MyBean jdkMyBean() {
        return new MyBean();
    }

    @Bean
    public MyBean onlyJdk() {
        return new MyBean();
    }

    // DefaultAdvisorAutoProxyCreator 정의 (우선순위 설정)
    @Bean
    @Order(2)
    public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    // Transaction attribute source advisor 정의
    // : @Transactional을 인식하는 포인트컷 + Advice(Interceptor) 연결
    @Bean
    public TransactionAttributeSourceAdvisor transactionAttributeSourceAdvisor(TransactionInterceptor transactionInterceptor) {
        TransactionAttributeSourceAdvisor advisor = new TransactionAttributeSourceAdvisor();
        advisor.setTransactionInterceptor(transactionInterceptor);
        return advisor;
    }

    // Spring AOP 기반의 선언적 트랜잭션 처리를 위해 핵심적으로 동작하는 
    // 트랜잭션 Advice(Interceptor)를 설정하는 부분
    // @Transactional이 붙은 메서드가 호출될 때 이 인터셉터가 가로채어 다음을 수행합니다:
    // - 트랜잭션 시작
    // - 타겟 클래스 메서드 실행
    // - 타겟 클래스 메서드 실행 결과, 예외 유무에 따라 커밋 또는 롤백
//    @Bean
//    public TransactionInterceptor transactionInterceptor() {
//        TransactionInterceptor interceptor = new TransactionInterceptor();
//        interceptor.setTransactionManager(transactionManager());
//        interceptor.setTransactionAttributeSource(
//        		new AnnotationTransactionAttributeSource());
//        return interceptor;
//    }

    // Transaction manager 정의
    @Bean
    public SimpleTransactionManager transactionManager() {
        return new SimpleTransactionManager();
    }

    // 예제 비즈니스 오브젝트 정의 (DefaultAdvisorAutoProxyCreator로 자동 프록시 생성)
    @Bean
    public BusinessObject1 businessObject1() {
        return new BusinessObject1();
    }

    @Bean
    public BusinessObject2 businessObject2() {
        return new BusinessObject2();
    }
}