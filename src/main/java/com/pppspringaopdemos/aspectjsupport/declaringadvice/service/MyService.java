package com.pppspringaopdemos.aspectjsupport.declaringadvice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void performTask() throws IndexOutOfBoundsException{
        System.out.println("Performing a task...");
        // 실제 작업 수행 로직
        //long dividedZero = 10 / 0;
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        
//        list.get(3);
        
        // @AfterThrowing 어드바이스가 적용되지 않음
        // : 예외를 처리하였기 때문에!!
        try {
        	list.get(3);
        } catch(IndexOutOfBoundsException e) {
        	System.out.println(e.getStackTrace());
        }

    }
}