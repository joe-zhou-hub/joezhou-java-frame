package com.joezhou.springboot2.service;

import com.joezhou.springboot2.pojo.Student;

/**
 * @author JoeZhou
 */
public interface StudentService {
    /**
     * 根据主键查询学生记录
     *
     * @param student 学生实体
     * @return 一条学生记录
     */
    Student findById(Student student);
}
