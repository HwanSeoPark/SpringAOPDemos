package com.pppspringaopdemos.proxyingmechanisms.cglibandobjenesis;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.Method;

public class CglibWithObjenesisDemo {
    public static void main(String[] args) {
    	Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);

        // ✅ 반드시 설정해야 함
        enhancer.setUseFactory(true);
        enhancer.setCallbackType(MethodInterceptor.class);  // ❗ 콜백 타입 명시!

        // ✅ 콜백 없이 프록시 클래스만 생성
        Class<?> proxyClass = enhancer.createClass();

        // ✅ 생성자 호출 없이 인스턴스 생성
        Target proxy = (Target) new ObjenesisStd().newInstance(proxyClass);

        // ✅ 생성 후 콜백 설정
        ((Factory) proxy).setCallback(0, new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("🔍 " + method.getName() + " 메서드 호출");
                return proxy.invokeSuper(obj, args);
            }
        });

        // ✅ 호출 테스트
        proxy.hello();
    }
}
