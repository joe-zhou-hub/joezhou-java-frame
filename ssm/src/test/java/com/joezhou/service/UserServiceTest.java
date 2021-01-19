package com.joezhou.service;

import com.joezhou.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author JoeZhou
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/app-mapper.xml", "classpath:spring/app-service.xml"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void paging() {
        System.out.println(userService.paging(1,5));
    }

    @Test
    public void selectById() {
        System.out.println(userService.selectById(1));
    }

    @Test
    public void insert() {
        User user = new User(null, "test-name", 2, 1);
        userService.insert(user);
        System.out.println(user);
    }

    @Test
    public void updateById() {
        userService.updateById(new User(10, "test-name-1", 3, 1));
    }

    @Test
    public void deleteByIds() {
        // userService.deleteByIds(new Integer[]{6, null});
        userService.deleteByIds(new Integer[]{6, 7});
    }
}