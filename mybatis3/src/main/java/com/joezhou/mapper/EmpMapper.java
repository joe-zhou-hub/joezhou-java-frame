package com.joezhou.mapper;

import com.joezhou.pojo.Emp;

import java.util.List;

/**
 * @author JoeZhou
 */
public interface EmpMapper {

    /**
     * 查询所有员工，同时查出员工所在部门信息
     *
     * @return 所有员工及其部门信息
     */
    List<Emp> findWithDept();

}