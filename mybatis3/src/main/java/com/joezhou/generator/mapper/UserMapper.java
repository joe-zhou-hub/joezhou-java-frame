package com.joezhou.generator.mapper;

import com.joezhou.generator.pojo.User;
import com.joezhou.generator.pojo.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JoeZhou
 */
public interface UserMapper {

    /**
     * 按条件查询结果集数量
     *
     * @param example User条件
     * @return 结果集数量
     */
    int countByExample(UserExample example);

    /**
     * 按条件删除数据
     *
     * @param example User条件
     * @return 影响条目数
     */
    int deleteByExample(UserExample example);

    /**
     * 按主键删除数据
     *
     * @param id 主键
     * @return 影响条目数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 静态添加一条数据
     *
     * @param record User实体
     * @return 影响条目数
     */
    int insert(User record);

    /**
     * 动态添加一条数据
     *
     * @param record User实体
     * @return 影响条目数
     */
    int insertSelective(User record);

    /**
     * 按条件查询数据
     *
     * @param example User条件
     * @return 结果集
     */
    List<User> selectByExample(UserExample example);

    /**
     * 按主键查询数据
     *
     * @param id 主键
     * @return 一条对应主键的数据
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 按条件动态修改数据
     *
     * @param record  User实体
     * @param example User条件
     * @return 影响的条目数
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * 按条件修改数据
     *
     * @param record  User实体
     * @param example User条件
     * @return 影响的条目数
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * 按主键动态修改数据
     *
     * @param record User实体
     * @return 影响的条目数
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 按主键修改数据
     *
     * @param record User实体
     * @return 影响的条目数
     */
    int updateByPrimaryKey(User record);
}