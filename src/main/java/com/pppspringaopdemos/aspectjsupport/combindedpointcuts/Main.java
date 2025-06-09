package com.pppspringaopdemos.aspectjsupport.combindedpointcuts;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pppspringaopdemos.aspectjsupport.combindedpointcuts.config.AppConfig;
import com.pppspringaopdemos.aspectjsupport.combindedpointcuts.service.dao.MyDao;
import com.pppspringaopdemos.aspectjsupport.combindedpointcuts.service.MyService;
import com.pppspringaopdemos.aspectjsupport.combindedpointcuts.web.WebController;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = 
        		new AnnotationConfigApplicationContext(AppConfig.class);

        // 웹 레이어 호출
        WebController webController = context.getBean(WebController.class);
        webController.handleRequest();

        // 서비스 레이어 호출
        MyService myService = context.getBean(MyService.class);
        myService.performBusinessLogic();

        // DAO 레이어 호출
        MyDao myDao = context.getBean(MyDao.class);
        myDao.accessData();
    }
}
