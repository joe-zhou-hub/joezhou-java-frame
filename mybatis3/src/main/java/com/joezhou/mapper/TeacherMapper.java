package com.joezhou.mapper;

import com.joezhou.pojo.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author JoeZhou
 */
public interface TeacherMapper {

    /**
     * 添加一条教师信息
     *
     * @param teacher 教师实体
     */
    @Insert("INSERT INTO `teacher` (`name`, `gender`, `age`, `info`) VALUES (#{name}, #{gender}, #{age}, #{info})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Teacher teacher);

    /**
     * 根据主键查询一条信息
     *
     * @param id 主键
     * @return 对应主键的一条信息
     */
    @Select("SELECT `id`, `name`, `gender`, `age`, `info` FROM `teacher` WHERE `id` = #{id}")
    Teacher findById(int id);

    /**
     * 按照姓名模糊查询所有符合条件的教师
     *
     * @param name 姓名的模糊查询部分
     * @return 所有符合条件的教师
     */
    @Select("SELECT `id`, `name`, `gender`, `age`, `info` FROM `teacher` WHERE `name` like concat('%', #{name}, '%')")
    List<Teacher> findLikeName(String name);

    /**
     * 根据主键修改一条教师信息
     *
     * @param teacher 教师实体
     */
    @Update("UPDATE `teacher` SET `name` = #{name}, `age` = #{age}, `gender` = #{gender}, `info` = #{info} WHERE `id` = #{id}")
    void updateById(Teacher teacher);

    /**
     * 根据主键删除教师
     *
     * @param id 主键
     */
    @Delete("DELETE FROM `teacher` WHERE `id` = #{id}")
    void deleteById(int id);
}