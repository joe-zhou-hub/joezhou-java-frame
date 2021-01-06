package com.joezhou.join;

import com.joezhou.mapper.EmpMapper;
import com.joezhou.pojo.Emp;
import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

/**
 * @author JoeZhou
 */
public class EmpTest {

    private SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-join.xml");

    @Test
    public void findWithDeptByJoin() {
        try (SqlSession session = factory.openSession()) {
            EmpMapper empMapper = session.getMapper(EmpMapper.class);
            for (Emp emp : empMapper.findWithDeptByJoin()) {
                System.out.printf("%s 在 %s 部门\n", emp.getEname(), emp.getDeptno().getDname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findWithDeptBySelect() {
        try (SqlSession session = factory.openSession()) {
            EmpMapper empMapper = session.getMapper(EmpMapper.class);
            for (Emp emp : empMapper.findWithDeptBySelect()) {
                System.out.printf("%s 在 %s 部门\n", emp.getEname(), emp.getDeptno().getDname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findWithDeptByOne() {
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
    public void findWithDeptByLazy() {
        try (SqlSession session = factory.openSession()) {
            EmpMapper empMapper = session.getMapper(EmpMapper.class);
            // debug here
            List<Emp> emps = empMapper.findWithDeptBySelect();
            System.out.println(emps.get(0).getEname());
            System.out.println(emps.get(0).getSal());
            System.out.println(emps.get(0).getDeptno().getDname());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}