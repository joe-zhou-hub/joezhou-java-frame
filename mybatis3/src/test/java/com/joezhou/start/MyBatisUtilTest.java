package com.joezhou.start;

import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author JoeZhou
 */
public class MyBatisUtilTest {

    @Test
    public void onlyRes() {
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
    public void resAndEnv() {
        SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-start.xml", "test");
        try (SqlSession session = factory.openSession()) {
            System.out.println(session.getConnection().isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void resAndProps() throws IOException {
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
    public void resAndEnvAndProps() throws IOException {
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
    public void refPropsByXml() {
        SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-properties.xml");
        try (SqlSession session = factory.openSession()) {
            System.out.println(session.getConnection().isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}