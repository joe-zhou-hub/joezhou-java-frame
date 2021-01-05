package com.joezhou.mapper;

import com.joezhou.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author JoeZhou
 */
public interface UserMapper {

    /**
     * 根据姓名模糊以及性别精准查询用户信息
     * 若姓名为null，则按性别精准查询
     * 若性别为null，则按姓名模糊查询
     *
     * @param user 用户实体
     * @return 满足条件的用户
     */
    List<User> findByIf(User user);

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
    List<User> findByWhere(User user);

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
    List<User> findByTrim(User user);

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
    List<User> findByChoose(User user);

    /**
     * 找到编号为3和4和6的用户
     *
     * @param ids 编号数组
     * @return 满足条件的用户
     */
    List<User> findWithArray(int[] ids);

    /**
     * 找到编号为3和4和6的用户
     *
     * @param ids 编号列表
     * @return 满足条件的用户
     */
    List<User> findWithList(List<Integer> ids);

    /**
     * 找到编号为3和4和6的用户
     *
     * @param userMap 用户信息集合
     * @return 满足条件的用户
     */
    List<User> findWithMap(Map<String, Object> userMap);

    /**
     * 根据主键修改用户信息
     *
     * @param user 用户实体
     */
    void updateBySet(User user);

    /**
     * 根据主键修改用户信息
     *
     * @param user 用户实体
     */
    void updateByTrim(User user);

    /**
     * 根据姓名模糊以及性别精准查询用户信息
     *
     * @param name   用户姓名
     * @param gender 用户性别
     * @return 满足条件的用户
     */
    List<User> findByNameAndGender(@Param("aa") String name, @Param("bb") int gender);

    /**
     * 根据姓名模糊查询用户信息
     *
     * @param name 姓名
     * @return 符合条件的用户信息
     */
    List<User> findBySingleParam(String name);

    /**
     * 根据姓名模糊和性别精准查询用户信息
     *
     * @param name   姓名
     * @param gender 性别
     * @return 符合条件的学生信息
     */
    List<User> findByMultipleParam(String name, int gender);

    /**
     * 根据姓名模糊和性别精准查询用户信息
     *
     * @param userA 用户实体
     * @param userB 用户实体
     * @return 符合条件的学生信息
     */
    List<User> findByPojoParam(User userA,User userB);
}