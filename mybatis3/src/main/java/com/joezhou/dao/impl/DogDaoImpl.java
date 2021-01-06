package com.joezhou.dao.impl;

import com.joezhou.dao.DogDao;
import com.joezhou.pojo.Dog;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * @author JoeZhou
 */
public class DogDaoImpl extends SqlSessionDaoSupport implements DogDao {

    @Override
    public void insert(Dog dog) {
        getSqlSession().insert("dogSpace.insert", dog);
    }
}