package com.joezhou.mapper;

import com.joezhou.pojo.User;

import java.util.List;

/**
 * @author JoeZhou
 */
public interface UserMapper {

    /**
     * 根据姓名模糊以及性别精准查询用户信息
     *
     * @param user 用户实体
     * @return 满足条件的用户
     */
    List<User> findLikeNameAndGender(User user);

}