package com.joezhou.mapper;

import com.joezhou.pojo.Dept;
import org.apache.ibatis.annotations.Select;

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
}