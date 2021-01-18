package com.joezhou.mapper;

import com.joezhou.pojo.User;

/**
 * @author JoeZhou
 */
public interface UserMapper {
    /**
     * 根据主键查询用户信息
     *
     * @param id 主键
     * @return 单条用户信息
     */
    User findById(int id);
}