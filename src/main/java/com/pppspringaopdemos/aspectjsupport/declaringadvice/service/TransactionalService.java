package com.pppspringaopdemos.aspectjsupport.declaringadvice.service;

import org.springframework.stereotype.Service;

@Service
public class TransactionalService {

    public void performTransactionalOperation() {
        System.out.println("Performing transactional operation...");
        // 트랜잭션 관련 비즈니스 로직
    }
}