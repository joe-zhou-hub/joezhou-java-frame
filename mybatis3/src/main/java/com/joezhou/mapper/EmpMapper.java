package com.joezhou.mapper;

import com.joezhou.pojo.Dept;
import com.joezhou.pojo.Emp;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

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
    List<Emp> findWithDeptByJoin();

    /**
     * 查询所有员工，同时查出员工所在部门信息
     *
     * @return 所有员工及其部门信息
     */
    List<Emp> findWithDeptBySelect();

    /**
     * 查询所有员工，同时查出员工所在部门信息
     *
     * @return 所有员工及其部门信息
     */
    @Select("SELECT `empno`, `ename`, `job`, `mgr`, `hiredate`, `sal`, `comm`, `deptno` FROM `emp`")
    @Results(id = "emp-dept-one", value = {
            // empno可以不用配置
            @Result(column = "empno", property = "empno", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "deptno", property = "deptno", javaType = Dept.class,
                    one = @One(select = "com.joezhou.mapper.DeptMapper.findByDeptNo")
            )
    })
    List<Emp> findWithDeptByOne();

}