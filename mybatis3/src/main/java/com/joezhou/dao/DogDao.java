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
}