package com.joezhou.crud;

import com.joezhou.pojo.Student;
import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * @author JoeZhou
 */
public class StudentTest {

    @Test
    public void insert() {
        SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-crud.xml");

        Student zhaosi = new Student(null, "赵四", 1, 58, "亚洲舞王");
        Student liunneng = new Student(10, "刘能", 0, 19, "玉田花圃");
        SqlSession session = factory.openSession();
        try {
            session.insert("studentSpace.insert", zhaosi);
            session.insert("studentSpace.insert", liunneng);
            session.commit();
            System.out.println(zhaosi);
            System.out.println(liunneng);
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void insertWithSelectKey() {
        SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-crud.xml");
        Student dajiao = new Student(null, "大脚", 0, 18, "大脚超市");
        SqlSession session = factory.openSession();
        try {
            session.insert("studentSpace.insertWithSelectKey", dajiao);
            session.commit();
            System.out.println(dajiao);
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void selectOne() {
        SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-crud.xml");
        Student zhaosi = new Student();
        zhaosi.setId(1);
        try (SqlSession session = factory.openSession();) {
            Student resultA = session.selectOne("studentSpace.findById", 1);
            Student resultB = session.selectOne("studentSpace.findById", zhaosi);
            session.commit();
            System.out.println(resultA);
            System.out.println(resultB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}