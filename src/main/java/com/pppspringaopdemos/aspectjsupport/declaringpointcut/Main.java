package com.pppspringaopdemos.aspectjsupport.declaringpointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pppspringaopdemos.aspectjsupport.declaringpointcut.config.AppConfig;
import com.pppspringaopdemos.aspectjsupport.declaringpointcut.service.MyService;
import com.pppspringaopdemos.aspectjsupport.declaringpointcut.service.SpecialService;
import com.pppspringaopdemos.aspectjsupport.declaringpointcut.service.TransferService;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = 
        		new AnnotationConfigApplicationContext(AppConfig.class);
        TransferService transferService = context.getBean(TransferService.class);
        SpecialService specialService = context.getBean(SpecialService.class);
        
        // TransferService 메서드 호출
        transferService.transfer("12345", 1000.00);
        transferService.checkBalance();
        
        // SpecialService 메서드 호출
        specialService.specialOperation("Upgrade");
        specialService.anotherSpecialOperation("Parameter");
        
        MyService service = (MyService) context.getBean("myServiceBean");
        service.serve();
    }
}