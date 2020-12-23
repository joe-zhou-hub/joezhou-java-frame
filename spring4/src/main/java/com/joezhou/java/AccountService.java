package com.joezhou.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JoeZhou
 */
@Service
public class AccountService {

    private AccountDao accountDao;

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> select() {
        return accountDao.select();
    }
}