package com.joezhou.sm;

import com.joezhou.mapper.CatMapper;
import com.joezhou.pojo.Cat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author JoeZhou
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-cat.xml")
public class CatTest {

    @Autowired
    private CatMapper catMapper;

    @Test
    public void insert() {
        catMapper.insert(new Cat(1, "加肥", 1, 5, "加菲猫"));
    }

    @Test
    public void findById() {
        System.out.println(catMapper.findById(1));
    }

    @Test
    public void updateById() {
        catMapper.updateById(new Cat(1, "汤姆", 2, 15, "英短"));
    }

    @Test
    public void deleteById() {
        catMapper.deleteById(1);
    }
}