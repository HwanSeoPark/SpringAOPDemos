package com.pppspringaopdemos.aspectjsupport.combindedpointcuts.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // 공통 포인트컷을 결합하여 웹 레이어의 모든 public 메서드에 대한 포인트컷을 정의
    @Pointcut("com.intheeast.aspectjsupport.combindedpointcuts.pointcuts.CommonPointcuts.inWebLayer() && execution(public * *(..))")
    public void publicWebLayerOperation() {}

    // 공통 포인트컷을 결합하여 서비스 레이어에서 트랜잭션이 필요한 메서드 포인트컷 정의
    // 다음 포인트컷 표현식의 의미를 부분별 설명:
    // com.intheeast.aspectjsupport.combindedpointcuts.pointcuts.CommonPointcuts.businessService():
    //      이 부분은 CommonPointcuts 클래스에서 정의된 businessService 포인트컷을 참조.
    //      이 포인트컷은 com.intheeast.aspectjsupport.combindedpointcuts.service 패키지 내의 모든 클래스에 있는 메서드를 대상으로 함.
    //
    // &&: 논리 AND 연산자입니다. 두 조건이 모두 참일 때만 포인트컷이 매칭됨.
    //
    // !com.intheeast.aspectjsupport.combindedpointcuts.pointcuts.CommonPointcuts.inDataAccessLayer():
    //       이 부분은 CommonPointcuts 클래스에서 정의된 inDataAccessLayer 포인트컷을 참조함.
    //       이 포인트컷은 com.intheeast.aspectjsupport.combindedpointcuts.dao 패키지 내의 모든 클래스에 있는 메서드를 대상으로 함. 
    //       !는 NOT 연산자로, 이 조건이 true가 아닌 경우에 포인트컷이 매칭된다는 의미.
    //
    // 전체 의미:
    // 이 포인트컷 표현식은 다음과 같은 조건을 만족하는 메서드를 대상으로 함:
    //   1. businessService 포인트컷에 의해 정의된 조건에 해당하는 메서드,
    //       즉 com.intheeast.aspectjsupport.combindedpointcuts.service 패키지 내의 모든 클래스의 메서드 중에서,
    //   2. inDataAccessLayer 포인트컷에 의해 정의된 조건에 해당하지 않는 메서드,
    //       즉 com.intheeast.aspectjsupport.combindedpointcuts.dao 패키지 내의 클래스에 속하지 않는 메서드.
    //
    //  간단히 말해, 서비스 레이어에 있는 메서드이지만, DAO(데이터 액세스) 레이어에 속하지 않는 메서드들을 대상으로 하는 포인트컷.
    //  이 조건에 해당하는 메서드들이 실행될 때 이 포인트컷이 매칭됨.
    @Pointcut("com.intheeast.aspectjsupport.combindedpointcuts.pointcuts.CommonPointcuts.businessService() && "
    		+ "!com.intheeast.aspectjsupport.combindedpointcuts.pointcuts.CommonPointcuts.inDataAccessLayer()")
    public void transactionalServiceOperation() {}

    // 웹 레이어의 public 메서드 또는 서비스 레이어의 메서드 중에서 데이터 액세스 레이어가 아닌 경우에 대한 포인트컷 정의
    @Pointcut("publicWebLayerOperation() || transactionalServiceOperation()")
    public void webOrTransactionalServiceOperation() {}

    // 웹 레이어의 모든 public 메서드 실행 전에 로그를 출력하는 어드바이스
    @Before("publicWebLayerOperation()")
    public void logBeforePublicWebOperation() {
        System.out.println("Logging before public web layer operation");
    }

    // 트랜잭션이 필요한 서비스 레이어 메서드 실행 전에 로그를 출력하는 어드바이스
    @Before("transactionalServiceOperation()")
    public void logBeforeTransactionalServiceOperation() {
        System.out.println("Logging before transactional service operation");
    }

    // 웹 레이어의 public 메서드 또는 트랜잭션이 필요한 서비스 레이어 메서드 실행 전에 로그를 출력하는 어드바이스
    @Before("webOrTransactionalServiceOperation()")
    public void logBeforeWebOrTransactionalServiceOperation() {
        System.out.println("Logging before web or transactional service operation");
    }
}