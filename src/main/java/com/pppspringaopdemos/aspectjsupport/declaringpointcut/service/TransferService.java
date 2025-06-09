package com.pppspringaopdemos.aspectjsupport.declaringpointcut.service;

import org.springframework.stereotype.Component;

@Component
public class TransferService {
	
	public void transfer(String account, double amount) {
		System.out.println("Transferring " + amount + " to account " + account);
    }

    public void checkBalance() {
        System.out.println("Checking balance");
    }

}
