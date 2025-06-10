package com.pppspringaopdemos.aspectjsupport.declaringpointcut.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	/*
	 anyTransferOperation(), 
	 withinTransferService(), 
	 proxyIsTransferService(), 
	 targetIsSpecialService(), 
	 methodWithStringArg(), 
	 loggableMethods(), 
	 withinSpecialComponent(), 
	 targetHasSpecialComponent(), 
	 methodWithValidatedArgs() 
	 위 메서드들은 실제로 호출되지 않으며, 포인트컷을 정의하는 역할(포인트컷 시그니쳐)을 합니다.
	 
	 단지, 다음의 어드바이스 메서드의 포인트컷으로 활용됩니다
	 예를 들어 logBeforeTransfer 메서드는 anyTransferOperation 포인트컷으로 타겟에 적용됩니다
	 logBeforeTransfer(), 
	 logBeforeWithinService(), 
	 logWhenProxyIsTransferService(), 
	 logWhenTargetIsSpecialService(), 
	 logForMethodsWithStringArg(), 
	 logForLoggableMethods(), 
	 logWithinSpecialComponent(), 
	 logWhenTargetHasSpecialComponent(), 
	 logForMethodsWithValidatedArgs() 
	 위 메서드들은 실제로 어드바이스(Advice)를 수행하는 메서드들입니다.
	 */

    // 특정 메서드 이름을 매칭하는 포인트컷
	                  // * 은 리턴값이 어떤게 오든 상관없다
	//("execution(* transfer(..))")의 의미는
	// 이름이 transfer이라는 메서드면 리턴값이 어떤게 오든
	// 파라미터정의가 몇개이든 상관없다는 의미
//    @Pointcut("execution(* transfer(..))") // 포인트컷 expression
//    private void anyTransferOperation() {} // 포인트컷 시크니쳐
//    // anyTransferOperation가 현재 Pointcut의 이름
//    
//    // 특정 클래스 내의 모든 메서드를 매칭하는 포인트컷
//    @Pointcut("within(com.pppspringaopdemos.aspectjsupport.declaringpointcut.service.TransferService)")
//    private void withinTransferService() {}
//
//    // this 디지그네이터를 사용하여 프록시 객체의 타입에 따라 매칭
//    @Pointcut("this(com.pppspringaopdemos.aspectjsupport.declaringpointcut.service.TransferService)")
//    private void proxyIsTransferService() {}
//
//    // target 디지그네이터를 사용하여 실제 타겟 객체의 타입에 따라 매칭
//    @Pointcut("target(com.pppspringaopdemos.aspectjsupport.declaringpointcut.service.SpecialService)")
//    private void targetIsSpecialService() {}

    // args 디지그네이터를 사용하여 메서드의 아규먼트가 특정 타입인 경우 매칭
    @Pointcut("args(String, ..)")
    private void methodWithStringArg() {}

    // @annotation 디지그네이터를 사용하여 메서드에 특정 애노테이션이 있는 경우 매칭
    @Pointcut("@annotation(com.pppspringaopdemos.aspectjsupport.declaringpointcut.annotation.Loggable)")
    private void loggableMethods() {}

    // @within 디지그네이터를 사용하여 클래스에 특정 애노테이션이 있는 경우 매칭
    @Pointcut("@within(com.pppspringaopdemos.aspectjsupport.declaringpointcut.annotation.SpecialComponent)")
    private void withinSpecialComponent() {}

    // @target 디지그네이터를 사용하여 실제 타겟 객체가 특정 애노테이션을 가지고 있는 경우 매칭
    @Pointcut("@target(com.pppspringaopdemos.aspectjsupport.declaringpointcut.annotation.SpecialComponent)")
    private void targetHasSpecialComponent() {}

    // @args 디지그네이터를 사용하여 메서드의 인자가 특정 애노테이션을 가진 타입인 경우 매칭
    @Pointcut("@args(com.pppspringaopdemos.aspectjsupport.declaringpointcut.annotation.Validated)")
    private void methodWithValidatedArgs() {}
    
    

    // 어드바이스 정의
    // transfer 메서드가 실행되기 전에 메서드 이름, 전달된 아규먼트, 타겟 클래스의 정보를 로그로 출력합니다.
//    @Before("anyTransferOperation()")
//    public void logBeforeTransfer(JoinPoint joinPoint) {
//        System.out.println("Logging before transfer operation");
//        System.out.println("Method: " + joinPoint.getSignature().getName());
//        System.out.println("Arguments: " + Arrays.toString(joinPoint.getArgs()));
//        System.out.println("Target class: " + joinPoint.getTarget().getClass().getName());
//    }
//
//    // TransferService 클래스 내의 모든 메서드 실행 전에 메서드 이름과 타겟 객체의 정보를 로그로 출력합니다.
//    @Before("withinTransferService()")
//    public void logBeforeWithinService(JoinPoint joinPoint) {
//        System.out.println("Logging before any method in TransferService");
//        System.out.println("Method: " + joinPoint.getSignature().getName());
//        System.out.println("Target object: " + joinPoint.getTarget());
//    }
//
//    // TransferService 타입의 프록시가 사용될 때, 프록시 클래스 이름과 호출되는 메서드 이름을 로그로 출력합니다.
//    @Before("proxyIsTransferService()")
//    public void logWhenProxyIsTransferService(JoinPoint joinPoint) {
//        Object proxy = joinPoint.getThis(); // 프록시 객체를 가져옴
//        System.out.println("Logging when proxy is of type TransferService");
//        System.out.println("Proxy class: " + proxy.getClass().getName()); // 프록시 클래스 이름 출력
//    }
//
//    // 타겟 객체가 SpecialService일 때, 타겟 클래스와 호출되는 메서드 이름을 로그로 출력합니다.
//    @Before("targetIsSpecialService()")
//    public void logWhenTargetIsSpecialService(JoinPoint joinPoint) {
//        System.out.println("Logging when target is of type SpecialService");
//        System.out.println("Target class: " + joinPoint.getTarget().getClass().getName());
//        System.out.println("Method being called: " + joinPoint.getSignature().getName());
//    }

    // 첫 번째 인자가 String인 메서드가 호출될 때, 메서드 이름과 전달된 인자 값을 로그로 출력합니다.
    @Before("methodWithStringArg()")
    public void logForMethodsWithStringArg(JoinPoint joinPoint) {
        System.out.println("Logging methods with String argument");
        System.out.println("Method: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: " + Arrays.toString(joinPoint.getArgs()));
    }

    // @Loggable 애노테이션이 붙은 메서드가 호출될 때, 메서드 이름과 타겟 클래스 정보를 로그로 출력합니다.
    @Before("loggableMethods()")
    public void logForLoggableMethods(JoinPoint joinPoint) {
        System.out.println("Logging for methods annotated with @Loggable");
        System.out.println("Method: " + joinPoint.getSignature().getName());
        System.out.println("Target class: " + joinPoint.getTarget().getClass().getName());
    }

    // @SpecialComponent 애노테이션이 붙은 클래스 내의 메서드가 호출될 때, 메서드 이름과 클래스 이름을 로그로 출력합니다.
    @Before("withinSpecialComponent()")
    public void logWithinSpecialComponent(JoinPoint joinPoint) {
        System.out.println("Logging for methods within a @SpecialComponent class");
        System.out.println("Method: " + joinPoint.getSignature().getName());
        System.out.println("Class: " + joinPoint.getTarget().getClass().getName());
    }

    // 타겟 객체가 @SpecialComponent 애노테이션을 가진 경우, 타겟 클래스와 호출된 메서드 이름을 로그로 출력합니다.
    @Before("targetHasSpecialComponent()")
    public void logWhenTargetHasSpecialComponent(JoinPoint joinPoint) {
        System.out.println("Logging for targets annotated with @SpecialComponent");
        System.out.println("Target class: " + joinPoint.getTarget().getClass().getName());
        System.out.println("Method being called: " + joinPoint.getSignature().getName());
    }

    // @Validated 애노테이션이 붙은 아규먼트가 있는 메서드가 호출될 때, 메서드 이름과 아규먼트 값을 로그로 출력합니다.
    @Before("methodWithValidatedArgs()")
    public void logForMethodsWithValidatedArgs(JoinPoint joinPoint) {
        System.out.println("Logging for methods with @Validated annotated arguments");
        System.out.println("Method: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: " + Arrays.toString(joinPoint.getArgs()));
    }
    
    @Before("bean(myServiceBean)")
    public void beforeBeanExecution() {
        System.out.println("🔥 Before advice applied to bean named 'myServiceBean'");
    }
}