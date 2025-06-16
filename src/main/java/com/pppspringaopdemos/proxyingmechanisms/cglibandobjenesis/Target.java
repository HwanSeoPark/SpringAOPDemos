package com.pppspringaopdemos.proxyingmechanisms.cglibandobjenesis;

public class Target {
    public Target() {
        System.out.println("🎯 Target 생성자 호출됨");
    }

    public void hello() {
        System.out.println("👋 Hello from Target");
    }
}
