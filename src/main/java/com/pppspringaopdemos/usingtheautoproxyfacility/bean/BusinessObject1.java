package com.pppspringaopdemos.usingtheautoproxyfacility.bean;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.pppspringaopdemos.usingtheautoproxyfacility.service.BusinessService;

public class BusinessObject1 implements BusinessService{

	/*
	 -@Transactional → AnnotationTransactionAttributeSource로 읽혀 트랜잭션 메타데이터 생성
	 -TransactionAttributeSourceAdvisor가 이 메타데이터와 TransactionInterceptor를 연결
     -DefaultAdvisorAutoProxyCreator가 이를 감지하여 프록시 생성
	 */
	@Transactional // Pointcut가 필요가없음
				   // @EnableTransactionManagement이 어노테이션을 선언해야함!!
	public void process() {
        System.out.println("Processing in BusinessObject1");
    }

}
