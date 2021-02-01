package com.joezhou.springboot2.service;

import com.joezhou.springboot2.pojo.Student;
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
    void findById(){
        Student student = new Student();
        student.setId(1);
        System.out.println(studentService.findById(student));
    }
}
