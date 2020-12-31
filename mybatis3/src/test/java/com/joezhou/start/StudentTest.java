package com.joezhou.start;

import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author JoeZhou
 */
public class StudentTest {

    @Test
    public void start() throws IOException {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(
                Resources.getResourceAsStream("mybatis-student.xml"));
        try (SqlSession session = sessionFactory.openSession()) {
            System.out.println(session.getConnection().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void myBatisUtil() {
        SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-student.xml");
        System.out.println(MyBatisUtil.getFactory("mybatis-student.xml").hashCode());
        System.out.println(MyBatisUtil.getFactory("mybatis-student.xml").hashCode());
        try (SqlSession session = factory.openSession()) {
            System.out.println(session.getConnection().isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}