package com.pppspringaopdemos.aspectjsupport.declaringpointcut.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProxyIsTransferService {

	// this 디지그네이터를 사용하여 프록시 객체의 타입에 따라 매칭
    @Pointcut("this(com.pppspringaopdemos.aspectjsupport.declaringpointcut.service.TransferService)")
    private void proxyIsTransferService() {}
    
    // TransferService 타입의 프록시가 사용될 때, 프록시 클래스 이름과 호출되는 메서드 이름을 로그로 출력합니다.
    @Before("proxyIsTransferService()")
    public void logWhenProxyIsTransferService(JoinPoint joinPoint) {
        Object proxy = joinPoint.getThis(); // 프록시 객체를 가져옴
        System.out.println("Logging when proxy is of type TransferService");
        System.out.println("Proxy class: " + proxy.getClass().getName()); // 프록시 클래스 이름 출력
    }

    // this 디지그네이터는 이렇게 MyServiceImpl는 못씀
//   @Pointcut("this(com.pppspringaopdemos.aspectjsupport.declaringpointcut.service.MyServiceImpl)")
//   private void proxyIsMyServiceImpl() {}

}
