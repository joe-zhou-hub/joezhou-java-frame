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
     * @param id 学生ID
     * @return 一条学生记录
     */
    Student findById(Integer id);
}
