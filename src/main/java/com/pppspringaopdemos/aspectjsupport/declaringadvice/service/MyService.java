package com.pppspringaopdemos.aspectjsupport.declaringadvice.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void performTask() {
        System.out.println("Performing a task...");
        // 실제 작업 수행 로직
        try {
            Thread.sleep(1000); // 1초 동안 대기 (작업 시뮬레이션)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}