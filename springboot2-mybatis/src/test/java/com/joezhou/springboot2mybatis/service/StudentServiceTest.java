package com.joezhou.springboot2mybatis.service;

import com.joezhou.springboot2mybatis.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author JoeZhou
 */
@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    void selectById(){
        Student student = new Student();
        student.setId(1);
        System.out.println(studentService.selectById(student));
    }
}
