package com.joezhou.mapper;

import com.joezhou.pojo.User;

import java.util.List;

/**
 * @author JoeZhou
 */
public interface UserMapper {

    /**
     * 查询全部用户信息
     *
     * @return 全部用户信息
     */
    List<User> selectAll();


    /**
     * 根据主键查询用户信息
     *
     * @param id 主键
     * @return 单条用户信息
     */
    User selectById(Integer id);

    /**
     * 添加用户
     *
     * @param user 用户实体
     */
    void insert(User user);

    /**
     * 根据主键修改用户
     *
     * @param user 用户实体
     */
    void updateById(User user);

    /**
     * 根据主键批量删除用户
     *
     * @param ids 主键数组
     */
    void deleteByIds(Integer[] ids);
}