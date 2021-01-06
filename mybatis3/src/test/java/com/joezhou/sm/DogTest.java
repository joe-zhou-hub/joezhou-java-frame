package com.joezhou.sm;

import com.joezhou.dao.DogDao;
import com.joezhou.pojo.Dog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author JoeZhou
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-sm.xml")
public class DogTest {

    @Autowired
    private DogDao dogDao;

    @Test
    public void insert() {
        dogDao.insert(new Dog(2, "旺财", 1, 5, "哈士奇"));
    }
}