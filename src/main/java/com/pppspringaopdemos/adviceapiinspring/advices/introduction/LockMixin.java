package com.pppspringaopdemos.adviceapiinspring.advices.introduction;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@SuppressWarnings("serial")
public class LockMixin extends DelegatingIntroductionInterceptor implements Lockable {

	// 현재 DelegatingIntroductionInterceptor 필드인 delegate는 LockMixin	
	// 여기서 delegate란?
	// LockMixin : Object that actually implements the interfaces.[ex) 여기서는 Lockable]
	// LockMixin : May be "this" if a subclass[LockMixin] implements the introduced interfaces.
    private boolean locked;

    @Override
    public void lock() {
        this.locked = true;
    }

    @Override
    public void unlock() {
        this.locked = false;
    }

    @Override
    public boolean locked() {
        return this.locked;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (locked() && invocation.getMethod().getName().startsWith("set")) {
            throw new LockedException();
        }
        return super.invoke(invocation);
    }
}