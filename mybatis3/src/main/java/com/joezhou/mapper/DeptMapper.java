package com.joezhou.mapper;

import com.joezhou.pojo.Dept;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author JoeZhou
 */
public interface DeptMapper {

    /**
     * 查询所有部门，同时查出部门所有员工信息
     *
     * @return 所有部门及其员工信息
     */
    List<Dept> findWithEmpsByJoin();

    /**
     * 查询所有部门，同时查出部门所有员工信息
     *
     * @return 所有部门及其员工信息
     */
    List<Dept> findWithEmpsBySelect();

    /**
     * 查询所有部门，同时查出部门所有员工信息
     *
     * @return 所有部门及其员工信息
     */
    @Select("SELECT `deptno`, `dname`, `loc` FROM `dept`")
    @Results(id = "dept-emp-many", value = {
            @Result(column = "deptno", property = "emps",
                    many = @Many(select = "com.joezhou.mapper.EmpMapper.findByDeptno")
            )
    })
    List<Dept> findWithEmpsByMany();
}