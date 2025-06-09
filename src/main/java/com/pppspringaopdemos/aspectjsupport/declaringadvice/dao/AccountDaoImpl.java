package com.pppspringaopdemos.aspectjsupport.declaringadvice.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pppspringaopdemos.aspectjsupport.declaringadvice.model.Account;

@Repository
public class AccountDaoImpl implements AccountDao {
    private Map<String, Account> accounts = new HashMap<>();

    @Override
    public Account findAccountById(String id) {
        return accounts.get(id);
    }

    @Override
    public void updateAccount(Account account) {
        accounts.put(account.getId(), account);
    }
}