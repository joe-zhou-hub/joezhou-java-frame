package com.joezhou.springboot2.mybatis;

import com.joezhou.springboot2.mybatis.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author JoeZhou
 */
@SpringBootTest
class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void selectById() {
        System.out.println(studentMapper.selectById(1));
    }
}
