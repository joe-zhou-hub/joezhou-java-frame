package com.joezhou.start;

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
public class StartTest {

    @Test
    public void start() throws IOException {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(
                Resources.getResourceAsStream("mybatis-start.xml"));
        try (SqlSession session = sessionFactory.openSession()) {
            System.out.println(session.getConnection().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}