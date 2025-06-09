package com.pppspringaopdemos.aspectjsupport.declaringadvice.model;

public class Account {
    private String id;
    private String ownerName;
    private double balance;
    private String accountHolderName;
    
    public Account() {}
    
    public Account(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}    
	
	

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountHolderName='" + accountHolderName + '\'' +
                '}';
    }
}