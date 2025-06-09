package com.pppspringaopdemos.aspectjsupport.declaringadvice.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pppspringaopdemos.aspectjsupport.declaringadvice.annotation.Auditable;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.dao.AccountDao;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.model.Account;

@Auditable("accountServiceClass")
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    
//    @Auditable("accountCreation")
    public void createAccount(String accountId) {
        System.out.println("Account created: " + accountId);
    }

    public void deleteAccount(String accountId) {
        System.out.println("Account deleted: " + accountId);
    }

    @Override
    public Account getAccount(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Account ID cannot be null");
        }
        return accountDao.findAccountById(id);
    }

    @Auditable("accountUpdate")
    @Override
    public void updateAccount(Account account) {
    	if (account.getBalance() < 0) {
            throw new IllegalArgumentException("Account balance cannot be negative");
        }
        accountDao.updateAccount(account);
    }
    
    public List<Account> findAccounts(String accountHolderNamePattern) {
        // 실제 데이터 접근 로직은 여기에 구현
        System.out.println("Finding accounts with pattern: " + accountHolderNamePattern);
        return Arrays.asList(new Account("John Doe"), new Account("Jane Doe"));
    }
}