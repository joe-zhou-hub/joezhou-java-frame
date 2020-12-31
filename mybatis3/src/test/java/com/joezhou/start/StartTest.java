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

    @Test
    public void myBatisUtil() {
        SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-start.xml");
        System.out.println(MyBatisUtil.getFactory("mybatis-start.xml").hashCode());
        System.out.println(MyBatisUtil.getFactory("mybatis-start.xml").hashCode());
        try (SqlSession session = factory.openSession()) {
            System.out.println(session.getConnection().isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void myBatisUtilWithEnv() {
        SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-start.xml", "test");
        try (SqlSession session = factory.openSession()) {
            System.out.println(session.getConnection().isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void myBatisUtilWithProps() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/jdbc/db.properties"));
        SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-start.xml", props);
        try (SqlSession session = factory.openSession()) {
            System.out.println(session.getConnection().isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void myBatisUtilWithEnvAndProps() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/jdbc/db.properties"));
        SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-start.xml", "test", props);
        try (SqlSession session = factory.openSession()) {
            System.out.println(session.getConnection().isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void usePropertiesWithXml() {
        SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-properties.xml");
        try (SqlSession session = factory.openSession()) {
            System.out.println(session.getConnection().isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}