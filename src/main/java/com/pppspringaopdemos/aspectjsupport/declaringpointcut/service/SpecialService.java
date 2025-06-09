package com.pppspringaopdemos.aspectjsupport.declaringpointcut.service;

import org.springframework.stereotype.Component;

import com.pppspringaopdemos.aspectjsupport.declaringpointcut.annotation.Loggable;
import com.pppspringaopdemos.aspectjsupport.declaringpointcut.annotation.SpecialComponent;
import com.pppspringaopdemos.aspectjsupport.declaringpointcut.annotation.Validated;

@SpecialComponent
@Component
public class SpecialService {
	
    @Loggable
    public void specialOperation(String operation) {
        System.out.println("Performing special operation: " + operation);
    }

    public void anotherSpecialOperation(@Validated String parameter) {
        System.out.println("Performing another special operation with parameter: " + parameter);
    }


}
