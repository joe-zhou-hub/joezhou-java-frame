package com.joezhou.springboot2.controller;

import com.joezhou.springboot2.pojo.Student;
import com.joezhou.springboot2.service.StudentService;
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

    @RequestMapping("find-by-id")
    public Student findById(Student student) {
        return studentService.findById(student);
    }

}
