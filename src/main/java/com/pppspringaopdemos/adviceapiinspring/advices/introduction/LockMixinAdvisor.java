package com.pppspringaopdemos.adviceapiinspring.advices.introduction;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

// 
@SuppressWarnings("serial")
public class LockMixinAdvisor extends DefaultIntroductionAdvisor {

	// 타겟 클래래스에게 어드바이스를 도입[Introduction] 시키고 있다
	// 그래서 JoinPoint가 필요없다
    public LockMixinAdvisor() {
    	// Introduction은 조인 포인트가 필요하지 않습니다.
    	// 1st para : 적용할 the Advice
    	// 2nd para : 도입할 interface
    	// introduction이 도입될 타겟은 2nd para의 아규먼트(도입할 인터페이스)가 적용(도입)된다.
        super(new LockMixin(), Lockable.class);
        // super을 사용해
        // DefaultIntroductionAdvisor의 컨스트럭터를 호출하고 있다
        
    }
}