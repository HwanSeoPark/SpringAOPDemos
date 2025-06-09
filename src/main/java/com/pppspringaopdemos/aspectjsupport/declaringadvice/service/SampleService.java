package com.pppspringaopdemos.aspectjsupport.declaringadvice.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.pppspringaopdemos.aspectjsupport.declaringadvice.annotation.AuditCode;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.annotation.AuditableCode;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.model.MyType;

@Service
public class SampleService implements Sample<MyType> {
	public SampleService() {
		System.out.println("SampleService default constructor!");
	}
	
	@AuditableCode(AuditCode.USER_ACTION)
    public void sampleMethod(String data) {
        System.out.println("Executing sampleMethod with data: " + data);
    }

    public void sampleGenericMethod(MyType param) {
        System.out.println("sampleGenericMethod called with param: " + param);
    }

    public void sampleGenericCollectionMethod(Collection<MyType> param) {
        System.out.println("sampleGenericCollectionMethod called with param: " + param);
    }	
}