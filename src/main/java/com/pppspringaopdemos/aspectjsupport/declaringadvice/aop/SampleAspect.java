package com.pppspringaopdemos.aspectjsupport.declaringadvice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.pppspringaopdemos.aspectjsupport.declaringadvice.annotation.AuditCode;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.annotation.Auditable;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.annotation.AuditableCode;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.model.MyType;

import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Collection;

@Aspect
@Component
public class SampleAspect {

    // 특정 타입의 파라미터를 가진 메서드에 어드바이스 적용
	// com.intheeast.aspectj.declaringadvice.service.Sample+:
	// 이 표현식은 Sample 인터페이스와 그 하위 타입(즉, Sample을 구현한 모든 클래스)을 의미합니다.
	// &&의 역할: AspectJ에서 &&는 두 개의 포인트컷 표현식을 결합하는 데 사용
	// *: 메서드의 파라미터가 하나만 있는 경우를 나타냅니다. 이 표현식은 단일 파라미터를 가지는 메서드에만 일치합니다.
    @Before("execution(* com.pppspringaopdemos.aspectjsupport.declaringadvice.service.Sample+.sampleGenericMethod(*)) && args(param)")
    public void beforeSampleMethod(JoinPoint joinPoint, MyType param) {
        System.out.println("Before sampleGenericMethod with MyType param: " + param);
    }

    // 제네릭 컬렉션 타입의 파라미터를 가진 메서드에 어드바이스 적용
    // com.intheeast.aspectj.declaringadvice.service.Sample+:
 	// 이 표현식은 Sample 인터페이스와 그 하위 타입(즉, Sample을 구현한 모든 클래스)을 의미합니다.
	// &&의 역할: AspectJ에서 &&는 두 개의 포인트컷 표현식을 결합하는 데 사용
    // *: 메서드의 파라미터가 하나만 있는 경우를 나타냅니다. 이 표현식은 단일 파라미터를 가지는 메서드에만 일치합니다.
    @Before("execution(* com.pppspringaopdemos.aspectjsupport.declaringadvice.service.Sample+.sampleGenericCollectionMethod(*)) && args(param)")
    public void beforeSampleGenericCollectionMethod(JoinPoint joinPoint, Collection<?> param) {
        System.out.println("Before sampleGenericCollectionMethod with Collection param: " + param);
        
        // 수동으로 Collection 내부 요소의 타입 확인
        if (!param.isEmpty()) {
            Object firstElement = param.iterator().next();
            if (firstElement instanceof MyType) {
                System.out.println("First element in collection is of type MyType: " + firstElement);
            }
        }
    }
    
    // Determining Argument Names
	// &&의 역할: AspectJ에서 &&는 두 개의 포인트컷 표현식을 결합하는 데 사용
    // (..): 메서드의 파라미터 개수와 타입에 상관없이 일치합니다. 즉, 파라미터가 0개일 수도 있고, 여러 개일 수도 있습니다.
    @Before("execution(* com.pppspringaopdemos.aspectjsupport.declaringadvice.service.Sample+.sampleGenericMethod(..)) && args(param)")
    public void beforeSampleMethodForDetermingArgumentName(JoinPoint joinPoint, MyType param) {

    	MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        
        ParameterNameDiscoverer pnd = new StandardReflectionParameterNameDiscoverer();
        String[] parameterNames = pnd.getParameterNames(method);

        if (parameterNames != null) {
            for (String paramName : parameterNames) {
                System.out.println("Determined parameter name: " + paramName);
            }
        } else {
            System.out.println("No parameter names found.");
        }

        System.out.println("Before sampleGenericMethod with MyType param: " + param);
    }
    
    // Explicit Argument Names
    @Before(
            value = "execution(* com.pppspringaopdemos.aspectjsupport.declaringadvice.service.SampleService.*(..)) && target(bean) && @annotation(auditable)",
            argNames = "bean,auditable")
    public void audit(Object bean, Auditable auditable) {
        String code = auditable.value();
        System.out.println("Audit - Bean: " + bean.getClass().getName() + ", Code: " + code);
        // Additional audit logic here
    }


    @Before(
            value = "execution(* com.pppspringaopdemos.aspectjsupport.declaringadvice.service.SampleService.*(..)) && target(bean) && @annotation(auditablecode)",
            argNames = "jp,bean,auditablecode")
    public void audit(JoinPoint jp, Object bean, AuditableCode auditablecode) {
        AuditCode code = auditablecode.value();
        System.out.println("Audit - Method: " + jp.getSignature().getName() + ", Bean: " + bean.getClass().getName() + ", Code: " + code);
        // Additional audit logic here
    }

}