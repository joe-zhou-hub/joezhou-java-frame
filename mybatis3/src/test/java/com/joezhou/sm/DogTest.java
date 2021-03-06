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
@ContextConfiguration("classpath:spring/spring-dog.xml")
public class DogTest {

    @Autowired
    private DogDao dogDao;

    @Test
    public void insert() {
        dogDao.insert(new Dog(1, "旺财", 1, 5, "哈士奇"));
    }

    @Test
    public void findById() {
        System.out.println(dogDao.findById(1));
    }

    @Test
    public void updateById() {
        dogDao.updateById(new Dog(1, "大黑", 2, 15, "柯基"));
    }

    @Test
    public void deleteById() {
        dogDao.deleteById(1);
    }
}