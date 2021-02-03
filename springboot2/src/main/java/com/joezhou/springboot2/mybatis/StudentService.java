package com.joezhou.springboot2.mybatis;

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
    Student selectById(Student student);
}
