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

    /**
     * 根据姓名模糊以及性别精准查询用户信息
     * 若姓名为null，则按性别精准查询
     * 若性别为null，则按姓名模糊查询
     *
     * @param user 用户实体
     * @return 满足条件的用户
     */
    List<User> findLikeNameAndGenderByIf(User user);

    /**
     * 根据姓名模糊以及性别精准查询用户信息
     * 若姓名为null，则按性别精准查询
     * 若性别为null，则按姓名模糊查询
     * 若姓名和性别均不为null，则按姓名模糊且按性别精准查询
     * 若姓名和性别均为null，全查
     *
     * @param user 用户实体
     * @return 满足条件的用户
     */
    List<User> findLikeNameAndGenderByWhere(User user);

}