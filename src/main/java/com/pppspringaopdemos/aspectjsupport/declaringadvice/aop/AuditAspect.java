package com.pppspringaopdemos.aspectjsupport.declaringadvice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.pppspringaopdemos.aspectjsupport.declaringadvice.annotation.Auditable;

@Aspect
@Component
public class AuditAspect {

    // @within: 특정 애노테이션이 클래스 레벨에서 적용된 모든 메서드에 매칭
    @Pointcut("@within(com.pppspringaopdemos.aspectjsupport.declaringadvice.annotation.Auditable)")
    public void withinAuditableClass() {}

    // @target: 특정 애노테이션이 적용된 객체의 모든 메서드에 매칭
    // 이 포인트컷은 런타임 시점에 프록시 객체가 생성될 때, 
    // 해당 객체의 클래스가 @Auditable 애노테이션을 가지고 있는지 확인. 
    // 그 후 해당 객체의 모든 메서드가 포인트컷에 매칭됩니다.
    @Pointcut("@target(com.pppspringaopdemos.aspectjsupport.declaringadvice.annotation.Auditable)")
    public void targetAuditableClass() {}

    // @annotation: 특정 애노테이션[@Auditable]이 적용된 메서드에 매칭
    // 시그니처에 파라미터 정의가 있는 경우는,
    // 어드바이스 메서드에서 @Auditable 애노테이션 객체 자체를 직접 받기 위해서입니다.
    @Pointcut("@annotation(auditable)")
    public void methodWithAuditableAnnotation(Auditable auditable) {}

    // (..): 메서드의 파라미터 개수와 타입에 상관없이 일치. 
    // 즉, 파라미터가 0개일 수도 있고, 여러 개일 수도 있습니다.
    // @args: 특정 타입의 인자를 전달받는 메서드에 매칭
    // @args(com.intheeast.aspectjsupport.declaringadvice.annotation.Auditable)
    //  : 이 애노테이션이 파라미터로 전달되는 객체에 적용되어 있어야 한다는 의미입니다.
    @Pointcut("execution(* com.pppspringaopdemos.aspectjsupport.declaringadvice.service.*.*(..)) && "
    		+ "@args(com.pppspringaopdemos.aspectjsupport.declaringadvice.annotation.Auditable)")
    public void methodWithAuditableArgs() {}

    // @within 포인트컷을 사용하는 어드바이스
    @Before("withinAuditableClass()")
    public void logWithinAuditableClass(JoinPoint joinPoint) {
        System.out.println("Within Auditable Class: " + joinPoint.getSignature().getName());
    }

    // @target 포인트컷을 사용하는 어드바이스
    @Before("targetAuditableClass()")
    public void logTargetAuditableClass(JoinPoint joinPoint) {
        System.out.println("Target Auditable Class: " + joinPoint.getSignature().getName());
    }

    // @annotation 포인트컷을 사용하는 어드바이스
    @Before("methodWithAuditableAnnotation(auditable)")
    public void logAuditOperation(JoinPoint joinPoint, Auditable auditable) {
        System.out.println("Auditing operation: " + auditable.value() +
                           ", Method: " + joinPoint.getSignature().getName());
    }

    // @args 포인트컷을 사용하는 어드바이스
    @Before("methodWithAuditableArgs()")
    public void logMethodWithAuditableArgs(JoinPoint joinPoint) {
        System.out.println("Method with Auditable Args: " 
        		+ joinPoint.getSignature().getName());
    }
}