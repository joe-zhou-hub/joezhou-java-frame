package com.joezhou.springboot2mybatis.service.impl;

import com.joezhou.springboot2mybatis.mapper.StudentMapper;
import com.joezhou.springboot2mybatis.pojo.Student;
import com.joezhou.springboot2mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JoeZhou
 */
@Service
public class StudentServiceImpl implements StudentService {

    private StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public Student selectById(Student student) {
        Integer id;
        if (student == null || (id = student.getId()) == null) {
            return new Student();
        }
        return studentMapper.selectById(id);
    }
}
