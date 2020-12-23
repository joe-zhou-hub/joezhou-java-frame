package com.joezhou.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoeZhou
 *
 * 本次为了学习如何使用 `@Bean` 来配置 `<bean>`，故替换掉 `@Repository` 去掉了。
 */
public class AccountDao {
    /**
     * 模拟10个账号的数据
     *
     * @return 10个账号的数据
     */
    public List<Account> select() {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0, j = 10; i < j; i++) {
            accounts.add(new Account(i, "账号" + i, "密码" + i));
        }
        return accounts;
    }
}