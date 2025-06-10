package com.pppspringaopdemos.aspectjsupport.enablingaspectjsupport.aopdemo.service;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public void sayHello() {
        System.out.println("Hello from GreetingServiceImpl");
    }
}
