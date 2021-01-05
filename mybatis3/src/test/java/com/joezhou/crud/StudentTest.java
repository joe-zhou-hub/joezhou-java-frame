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

    private SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-crud.xml");

    @Test
    public void insert() {
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
    public void findById() {
        try (SqlSession session = factory.openSession();) {
            System.out.println((Student) session.selectOne(
                    "studentSpace.findById", 1));
            System.out.println((Student) session.selectOne(
                    "studentSpace.findById", new Student(1, null, null, null, null)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findLikeName() {
        try (SqlSession session = factory.openSession()) {
            System.out.println(session.selectList(
                    "studentSpace.findLikeName", "刘"));
            System.out.println(session.selectList(
                    "studentSpace.findLikeName", "'or'"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findLikeNameWithConcat() {
        try (SqlSession session = factory.openSession()) {
            System.out.println(session.selectList(
                    "studentSpace.findLikeNameWithConcat", "刘"));
            System.out.println(session.selectList(
                    "studentSpace.findLikeNameWithConcat", "'or'"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateById() {
        SqlSession session = factory.openSession();
        Student zhaosi = new Student(1, "谢广坤", 1, 60, "广坤山货");
        try {
            session.update("studentSpace.updateById", zhaosi);
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
        try {
            session.delete("studentSpace.deleteById", 1);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void findAll() {
        try (SqlSession session = factory.openSession()) {
            System.out.println(session.selectList("studentSpace.findAll"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}