package com.joezhou.service;

import com.github.pagehelper.PageInfo;
import com.joezhou.pojo.User;

/**
 * @author JoeZhou
 */
public interface UserService {

    /**
     * 分页查询用户信息
     *
     * @param pageNum 显示第几页
     * @param pageSize 每页显示几条
     * @return 部分用户信息
     */
    PageInfo<User> paging(Integer pageNum, Integer pageSize);

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