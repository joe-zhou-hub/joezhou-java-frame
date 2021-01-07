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

    @Override
    public Dog findById(int id) {
        return getSqlSession().selectOne("dogSpace.findById", id);
    }

    @Override
    public void updateById(Dog dog) {
        getSqlSession().update("dogSpace.updateById", dog);
    }

    @Override
    public void deleteById(int id) {
        getSqlSession().delete("dogSpace.deleteById", id);
    }
}