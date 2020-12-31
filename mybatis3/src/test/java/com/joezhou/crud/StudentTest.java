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
        SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-student.xml");

        Student zhaosi = new Student(null, "赵四", 1, 58, "亚洲舞王");
        Student liunneng = new Student(5, "刘能", 0, 19, "玉田花圃");
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
}