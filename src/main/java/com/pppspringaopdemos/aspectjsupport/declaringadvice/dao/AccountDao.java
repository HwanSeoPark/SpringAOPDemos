package com.pppspringaopdemos.aspectjsupport.declaringadvice.dao;

import com.pppspringaopdemos.aspectjsupport.declaringadvice.model.Account;

public interface AccountDao {
    Account findAccountById(String id);
    void updateAccount(Account account);
}
