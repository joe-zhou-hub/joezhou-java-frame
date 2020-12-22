package com.joezhou.dao;

import com.joezhou.pojo.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoeZhou
 *
 * 原本数据层模型类上我们可以使用 `@Repository` 注解配合包扫描来完成自动配置 `<bean>`，
 * 但本次为了学习如何使用java代码中的 `@Bean` 的方式手动配置 `<bean>`，故将 `@Repository` 去掉了。
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