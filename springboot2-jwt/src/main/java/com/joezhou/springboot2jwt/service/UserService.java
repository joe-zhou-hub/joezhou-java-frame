package com.joezhou.springboot2jwt.service;

import com.joezhou.springboot2jwt.entity.User;

/**
 * @author JoeZhou
 */
public interface UserService {

    /**
     * 登录
     *
     * @param user 用户实体
     * @return 对应用户的一条记录
     */
    User login(User user);
}