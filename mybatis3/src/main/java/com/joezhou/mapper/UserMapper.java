package com.joezhou.mapper;

import com.joezhou.pojo.User;

import java.util.List;
import java.util.Map;

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
    List<User> findLikeNameAndGenderByTrim(User user);

    /**
     * 根据姓名模糊以及性别精准查询用户信息
     * id不为null时
     * 若姓名不为null，按姓名模糊查询
     * 若姓名为null，但性别不为null，则按性别精准查询
     * 若姓名和性别都为null，则按照id精准查询
     *
     * @param user 用户实体
     * @return 满足条件的用户
     */
    List<User> findLikeNameAndGenderByChoose(User user);

    /**
     * 找到编号为3和4和6的用户
     *
     * @param ids 编号数组
     * @return 满足条件的用户
     */
    List<User> findWithArrayByForEach(int[] ids);

    /**
     * 找到编号为3和4和6的用户
     *
     * @param ids 编号列表
     * @return 满足条件的用户
     */
    List<User> findWithListByForEach(List<Integer> ids);

    /**
     * 找到编号为3和4和6的用户
     *
     * @param userMap 用户信息集合
     * @return 满足条件的用户
     */
    List<User> findWithMapByForEach(Map<String, Object> userMap);

    /**
     * 根据主键修改用户信息
     *
     * @param user 用户实体
     */
    void updateByIdBySet(User user);

    /**
     * 根据主键修改用户信息
     *
     * @param user 用户实体
     */
    void updateByIdByTrim(User user);

}