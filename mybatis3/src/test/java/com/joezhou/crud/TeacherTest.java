package com.joezhou.crud;

import com.joezhou.mapper.TeacherMapper;
import com.joezhou.pojo.Teacher;
import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * @author JoeZhou
 */
public class TeacherTest {

    private SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-crud.xml");

    @Test
    public void insert() {
        SqlSession session = factory.openSession();
        TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
        try {
            teacherMapper.insert(new Teacher(null, "赵四", 1, 58, "亚洲舞王"));
            teacherMapper.insert(new Teacher(null, "赵五", 1, 58, "亚洲舞王"));
            teacherMapper.insert(new Teacher(null, "王四", 1, 58, "亚洲舞王"));
            teacherMapper.insert(new Teacher(null, "王五", 1, 58, "亚洲舞王"));
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void findById() {
        try (SqlSession session = factory.openSession()) {
            TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
            System.out.println(teacherMapper.findById(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findLikeName() {
        try (SqlSession session = factory.openSession()) {
            TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
            System.out.println(teacherMapper.findLikeName("四"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateById() {
        SqlSession session = factory.openSession();
        TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
        try {
            teacherMapper.updateById(new Teacher(1, "刘能", 1, 58, "村副主任"));
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void deleteById() {
        SqlSession session = factory.openSession();
        TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
        try {
            teacherMapper.deleteById(1);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}