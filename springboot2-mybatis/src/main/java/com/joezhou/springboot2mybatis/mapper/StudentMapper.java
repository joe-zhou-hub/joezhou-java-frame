package com.joezhou.springboot2mybatis.mapper;

import com.joezhou.springboot2mybatis.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
    @Select("<script>" +
            "select id, name, age, gender, info from student where " +
            "<if test='id != null'>id = #{id}</if>" +
            "</script>")
    Student selectById(@Param("id") Integer id);
}
