package com.joezhou.join;

import com.joezhou.mapper.EmpMapper;
import com.joezhou.pojo.Emp;
import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

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

}