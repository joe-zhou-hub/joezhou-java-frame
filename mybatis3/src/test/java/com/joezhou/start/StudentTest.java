package com.joezhou.start;

import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

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

    @Test
    public void useProperties() {
        SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-student.xml");
        try (SqlSession session = factory.openSession()) {
            System.out.println(session.getConnection().isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void usePropertiesInFactory() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/jdbc/db.properties"));
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(
                Resources.getResourceAsStream("mybatis-student.xml"), properties);
        try(SqlSession session = factory.openSession()) {
            System.out.println(session.getConnection().isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}