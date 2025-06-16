package com.pppspringaopdemos.pointcutapi.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import com.pppspringaopdemos.pointcutapi.service.CustomAnnotation;

// Pointcut운 springframework에서 정의한걸 쓰고 있다
public class CustomPointcut implements Pointcut {

    @Override
    public ClassFilter getClassFilter() {
    	// startsWiths는 해당 패키지 내의 모든 클래스에 대해 true를 리턴함
        return clazz -> clazz.getName().startsWith("com.pppspringaopdemos.pointcutapi.service");
    }					// 여기서 원하는 클래스 이름은 FQCN
    					// statsWith : 적용될 클래스들의 이름은
    					// com.pppspringaopdemos.pointcutapi.service로 시작하는
                        // 모든 클래스
    					// 그리고 그중에 MethodMatcher와 연관된 메서드들

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {

        	// isRuntime() true라도 이 matches메서드는 한번은 실행해서 캐시에 저장함
        	// 첫번째 용도. Spring AOP는 프록시 생성 시점에 matches(Method, Class)만 사용해서 Advisor 적용 여부를 미리 결정
        	// : matching 결과를 cache에 저장(프록시 생성 시점에)할 수 있음.(속도가빠름)
        	// 두번째 용도. isRuntime가 false를 리턴하면, 이 메서드가 사용됨.
        	@Override
            public boolean matches(Method method, Class<?> targetClass) {
                // 특정 어노테이션이 있는 메서드와 메서드 이름이 "differentMethod"인 경우 매칭
                return method.isAnnotationPresent(CustomAnnotation.class)
                        || "differentMethod".equals(method.getName());
                // CustomAnnotation 어노테이션이 적용되있거나
                // 또는 differentMethod라는 이름일때
                // 이 pointcut을 적용해라
            }

        	// return 값은 한개만 사용 가능
            @Override
            public boolean isRuntime() {
//                return true; // true로 설정하면 아래 matches 메서드가 호출됨
            	return false; // false로 설정하면 위의 matches 메서드가 호출됨
            }

            // 캐시에 저장되어 있는 매칭 정보를 사용하지 않고,
            // 런타임때 항상 체크하겠다라는 의미!!!
            @Override
            public boolean matches(Method method, Class<?> targetClass, Object... args) {
              	// args.length > 0 : 아규먼트가 최소 1개 이상이고(파라미터정의가 1개이상)
            	// 아규먼트가 String 타입인 경우 매칭
                if (args.length > 0 && args[0] instanceof String) {
                    return true;
                }
                return false;
            }
        };
    }
}
