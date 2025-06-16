package com.pppspringaopdemos.adviceapiinspring.advices;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class DebugInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Before: invocation=[" + invocation + "]");
        // Intercepter chain
        // [Intercepter1] → [Interceptor2] → ... → [Target Method]
        Object rval = invocation.proceed();
        System.out.println("Invocation returned with value: " + rval);
        return rval;
    }
}