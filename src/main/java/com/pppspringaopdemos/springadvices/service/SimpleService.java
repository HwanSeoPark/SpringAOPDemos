package com.pppspringaopdemos.springadvices.service;

public class SimpleService {

    public void performTask() {
        System.out.println("SimpleService:Performing a task");
    }

    public String returnGreeting(String name) {
        return "SimpleService:Hello, " + name;
    }

    public void throwException() throws Exception {
        throw new Exception("SimpleService:This is a test exception");
    }
}