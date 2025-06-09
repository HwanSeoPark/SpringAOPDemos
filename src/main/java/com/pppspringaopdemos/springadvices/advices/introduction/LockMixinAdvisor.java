package com.pppspringaopdemos.springadvices.advices.introduction;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

// 
public class LockMixinAdvisor extends DefaultIntroductionAdvisor {

    public LockMixinAdvisor() {
    	// 1st para : 적용할 the Advice
    	// 2nd para : 도입할 interface
        super(new LockMixin(), Lockable.class);
    }
}