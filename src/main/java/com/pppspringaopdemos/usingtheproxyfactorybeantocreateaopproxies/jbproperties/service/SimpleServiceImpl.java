package com.pppspringaopdemos.usingtheproxyfactorybeantocreateaopproxies.jbproperties.service;

public class SimpleServiceImpl implements SimpleService {
    @Override
    public void doSomething() {
        System.out.println("Doing something in SimpleService...");
    }
}