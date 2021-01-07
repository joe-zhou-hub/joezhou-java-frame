package com.joezhou.dao;

import com.joezhou.pojo.Dog;

/**
 * @author JoeZhou
 */
public interface DogDao {

    /**
     * 添加一只小狗
     *
     * @param dog 小狗实体
     */
    void insert(Dog dog);

    /**
     * 通过主键查询一只小狗
     *
     * @param id 主键
     * @return 一只小狗
     */
    Dog findById(int id);

    /**
     * 通过主键查询修改一只小狗
     *
     * @param dog 小狗实体
     */
    void updateById(Dog dog);

    /**
     * 通过主键查询删除一只小狗
     *
     * @param id 主键
     */
    void deleteById(int id);
}