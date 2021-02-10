package com.joezhou.springboot2mybatis.controller;

import com.joezhou.springboot2mybatis.pojo.Student;
import com.joezhou.springboot2mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("api/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("select-by-id")
    public Student selectById(Student student) {
        return studentService.selectById(student);
    }

}
