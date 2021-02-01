package com.joezhou.springboot2.mapper;

import com.joezhou.springboot2.pojo.Student;
import org.springframework.stereotype.Repository;

/**
 * @author JoeZhou
 */
@Repository
public interface StudentMapper {

    /**
     * 根据主键查询学生记录
     *
     * @param student 学生实体
     * @return 一条学生记录
     */
    Student findById(Student student);
}
