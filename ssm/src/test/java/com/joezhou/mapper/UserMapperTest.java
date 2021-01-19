package com.joezhou.mapper;

import com.joezhou.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/app-mapper.xml")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectAll() {
        System.out.println(userMapper.selectAll());
    }

    @Test
    public void selectById() {
        System.out.println(userMapper.selectById(1));
    }

    @Test
    public void insert() {
        User user = new User(null, "test-name", 2, 1);
        userMapper.insert(user);
        System.out.println(user);
    }

    @Test
    public void updateById() {
        User user = new User(21, "test-name-1", 3, 1);
        userMapper.updateById(user);
    }

    @Test
    public void deleteByIds() {
        userMapper.deleteByIds(new Integer[]{3, 4});
    }

}