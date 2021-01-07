package com.joezhou.mapper;

import com.joezhou.pojo.Cat;

/**
 * @author JoeZhou
 */
public interface CatMapper {

    /**
     * 添加一只小猫
     *
     * @param cat 小猫实体
     */
    void insert(Cat cat);

    /**
     * 根据主键查询一只小猫
     *
     * @param id 主键
     * @return 一只小猫
     */
    Cat findById(int id);

    /**
     * 根据主键修改一只小猫
     *
     * @param cat 小猫实体
     */
    void updateById(Cat cat);

    /**
     * 根据主键删除一只小猫
     *
     * @param id 主键
     */
    void deleteById(int id);
}