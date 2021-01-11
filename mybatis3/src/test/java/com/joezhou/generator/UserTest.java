package com.joezhou.generator;

import com.joezhou.generator.mapper.UserMapper;
import com.joezhou.generator.pojo.User;
import com.joezhou.generator.pojo.UserExample;
import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * @author JoeZhou
 */
public class UserTest {

    private SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-generator.xml");

    /**
     * insert into user (id, name, age, gender, info) values (?, ?, ?, ?, ?)
     * insert into user (name, age) values (?, ?)
     */
    @Test
    public void insert() {
        User liuneng = new User(null, "刘能", null, 58, null);
        User dajiao = new User(null, "大脚", null, 18, null);
        SqlSession session = factory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.insert(liuneng);
            userMapper.insertSelective(dajiao);
            session.commit();
            System.out.println(liuneng);
            System.out.println(dajiao);
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * update user set id = ?, name = ?, gender = ?, age = ?, info = ? where id = ?
     * update user set name = ? where id = ?
     */
    @Test
    public void updateByPrimaryKey() {
        SqlSession session = factory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.updateByPrimaryKey(new User(12, "刘能2", null, null, null));
            userMapper.updateByPrimaryKeySelective(new User(13, "大脚2", null, null, null));
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * update user set gender = 1 where name like '赵%' and gender <> 1
     */
    @Test
    public void updateByExample() {
        SqlSession session = factory.openSession();
        try {
            UserExample example = new UserExample();
            example.createCriteria().andNameLike("赵%").andGenderNotEqualTo(1);
            session.getMapper(UserMapper.class).updateByExampleSelective(
                    new User(null, null, 1, null, null), example);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * delete from user where id = ?
     */
    @Test
    public void deleteByPrimaryKey() {
        SqlSession session = factory.openSession();
        try {
            session.getMapper(UserMapper.class).deleteByPrimaryKey(11);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * delete from user where age is null or age not between 1 and 150
     */
    @Test
    public void deleteByExample() {
        SqlSession session = factory.openSession();
        try {
            UserExample example = new UserExample();
            example.or().andAgeIsNull();
            example.or().andAgeNotBetween(1, 150);
            // if(example != null)
            session.getMapper(UserMapper.class).deleteByExample(example);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * select id, name, gender, age, info from user where id = ?
     */
    @Test
    public void selectByPrimaryKey() {
        try (SqlSession session = factory.openSession()) {
            System.out.println(session.getMapper(UserMapper.class).selectByPrimaryKey(7));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * select id, name, gender, age, info from user where name like '赵%' and gender = 1
     * select id, name, gender, age, info from user
     */
    @Test
    public void selectByExample() {
        try (SqlSession session = factory.openSession()) {
            UserExample example = new UserExample();
            example.createCriteria().andNameLike("赵%").andGenderEqualTo(1);
            System.out.println(session.getMapper(UserMapper.class).selectByExample(example));
            System.out.println(session.getMapper(UserMapper.class).selectByExample(new UserExample()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * select count(*) from user where id >= 2
     */
    @Test
    public void countByExample() {
        try (SqlSession session = factory.openSession()) {
            UserExample example = new UserExample();
            example.createCriteria().andIdGreaterThanOrEqualTo(2);
            System.out.println(session.getMapper(UserMapper.class).countByExample(example));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * select id, name, gender, age, info from user order by age desc
     */
    @Test
    public void setOrderByClause() {
        try (SqlSession session = factory.openSession()) {
            UserExample example = new UserExample();
            example.setOrderByClause("age desc");
            System.out.println(session.getMapper(UserMapper.class).selectByExample(example));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * select distinct id, name, gender, age, info from user
     */
    @Test
    public void setDistinct() {
        try (SqlSession session = factory.openSession()) {
            UserExample example = new UserExample();
            example.setDistinct(true);
            System.out.println(session.getMapper(UserMapper.class).selectByExample(example));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
