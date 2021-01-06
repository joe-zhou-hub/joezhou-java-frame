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
    public void findWithDept() {
        try (SqlSession session = factory.openSession()) {
            EmpMapper empMapper = session.getMapper(EmpMapper.class);
            List<Emp> emps = empMapper.findWithDept();
            for (Emp emp : emps) {
                System.out.println(emp.getEname() + " : " + emp.getDeptno().getDname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}