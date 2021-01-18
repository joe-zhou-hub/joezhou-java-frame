package com.joezhou.service;

import com.joezhou.pojo.User;
import com.joezhou.util.PagingUtil;

import java.util.List;

/**
 * @author JoeZhou
 */
public interface UserService {

    /**
     * 分页查询用户信息
     *
     * @param pagingUtil SQL分页工具
     * @return 用户信息
     */
    List<User> paging(PagingUtil pagingUtil);

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