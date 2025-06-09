package com.pppspringaopdemos.usingtheautoproxyfacility.bean;

import org.springframework.transaction.annotation.Transactional;

import com.pppspringaopdemos.usingtheautoproxyfacility.service.BusinessService;

public class BusinessObject2 implements BusinessService{
	
	@Transactional
	public void process() {
        System.out.println("Processing in BusinessObject2");
    }
	
}
