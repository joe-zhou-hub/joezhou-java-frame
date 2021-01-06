package com.joezhou.mapper;

import com.joezhou.pojo.Dept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author JoeZhou
 */
public interface DeptMapper {
    /**
     * 通过主键查询部门信息
     * @param deptno 主键
     * @return 返回对应主键的部门信息
     */
    @Select("SELECT `deptno`, `dname`, `loc` FROM `dept` WHERE `deptno` = #{deptno}")
    Dept findByDeptno(int deptno);

    /**
     * 查询所有部门，同时查出部门所有员工信息
     *
     * @return 所有部门及其员工信息
     */
    List<Dept>  findWithEmpsByJoin();

    /**
     * 查询所有部门，同时查出部门所有员工信息
     *
     * @return 所有部门及其员工信息
     */
    List<Dept>  findWithEmpsBySelect();

    /**
     * 查询所有部门，同时查出部门所有员工信息
     *
     * @return 所有部门及其员工信息
     */
    List<Dept>  findWithEmpsByMany();
}