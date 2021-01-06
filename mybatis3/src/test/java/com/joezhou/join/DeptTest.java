package com.joezhou.join;

import com.joezhou.mapper.DeptMapper;
import com.joezhou.mapper.EmpMapper;
import com.joezhou.pojo.Dept;
import com.joezhou.pojo.Emp;
import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * @author JoeZhou
 */
public class DeptTest {

    private SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-join.xml");

    @Test
    public void findWithEmpsByJoin() {
        try (SqlSession session = factory.openSession()) {
            DeptMapper deptMapper = session.getMapper(DeptMapper.class);
            for (Dept dept : deptMapper.findWithEmpsByJoin()) {
                for (Emp emp : dept.getEmps()) {
                    System.out.print(emp.getEname() + " ");
                }
                System.out.println("在" + dept.getDname() + "部门");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findWithEmpsBySelect() {
        try (SqlSession session = factory.openSession()) {
            DeptMapper deptMapper = session.getMapper(DeptMapper.class);
            for (Dept dept : deptMapper.findWithEmpsBySelect()) {
                for (Emp emp : dept.getEmps()) {
                    System.out.print(emp.getEname() + " ");
                }
                System.out.printf("在 %s 部门\n", dept.getDname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findWithDeptByMany() {
        try (SqlSession session = factory.openSession()) {
            EmpMapper empMapper = session.getMapper(EmpMapper.class);
            for (Emp emp : empMapper.findWithDeptByOne()) {
                System.out.printf("%s 在 %s 部门\n", emp.getEname(), emp.getDeptno().getDname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findWithEmpsByLazy() {
        try (SqlSession session = factory.openSession()) {
            DeptMapper deptMapper = session.getMapper(DeptMapper.class);
            for (Dept dept : deptMapper.findWithEmpsBySelect()) {
                for (Emp emp : dept.getEmps()) {
                    System.out.print(emp.getEname() + " ");
                }
                System.out.printf("在 %s 部门\n", dept.getDname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}