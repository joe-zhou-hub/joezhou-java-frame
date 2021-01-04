package com.joezhou.crud;

import com.joezhou.pojo.Teacher;
import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * @author JoeZhou
 */
public class TeacherTest {

    @Test
    public void insert() {
        SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-teacher.xml");

        Teacher changgui = new Teacher(null, "长贵", 1, 88, "村长");
        SqlSession session = factory.openSession();
        try {
            session.insert("teacherSpace.insert", changgui);
            session.commit();
            System.out.println(changgui);
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}