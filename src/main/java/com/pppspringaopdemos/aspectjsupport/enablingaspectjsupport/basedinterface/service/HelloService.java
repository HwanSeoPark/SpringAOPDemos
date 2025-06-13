package com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.basedinterface.service;

import org.springframework.stereotype.Component;

//@Component
public class HelloService {

	private FooService fooService;
	
	
	public HelloService(FooService fooService) {
		this.fooService = fooService;
	}


	public FooService getFooService() {
		return fooService;
	}


	public void setFooService(FooService fooService) {
		this.fooService = fooService;
	}
	
	
}
