package com.joezhou.springdata2elasticsearch.es;

import com.joezhou.springdata2elasticsearch.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author JoeZhou
 */
@SpringBootTest
class EsApi {

    @Autowired
    private UserRepository userRepository;

    @Test
    void insert() {
        User user = new User(1, "测试姓名", "描述信息");
        userRepository.save(user);
    }
}
