package com.joezhou.generator;

import com.joezhou.generator.mapper.UserMapper;
import com.joezhou.generator.pojo.User;
import com.joezhou.generator.pojo.UserExample;
import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
     *
     */
    @Test
    public void selectByPrimaryKey() {
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        System.out.println(userMapper.selectByPrimaryKey(7));
        session.close();
    }

    /**
     * 按条件查询：查询姓赵的男性
     */
    @Test
    public void selectByExample() {
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("赵%");
        criteria.andGenderEqualTo(1);
        System.out.println(userMapper.selectByExample(example));
        session.close();
    }

    /**
     *
     */
    @Test
    public void select() {
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        System.out.println(userMapper.selectByExample(new UserExample()));
        session.close();
    }

    /**
     * 查询id大于等于2的人数有多少
     */
    @Test
    public void countByExample() {
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        UserExample example = new UserExample();
        example.createCriteria()
                .andIdGreaterThanOrEqualTo(2);
        int count = userMapper.countByExample(example);
        System.out.println(count);
        session.close();
    }

    /**
     *
     */
    @Test
    public void setOrderByClause() {
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        UserExample example = new UserExample();
        example.setOrderByClause("age desc");
        List<User> users = userMapper.selectByExample(example);
        System.out.println(users);
        session.close();
    }

    /**
     *
     */
    @Test
    public void setDistinct() {
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        UserExample example = new UserExample();
        example.setDistinct(false);
        List<User> users = userMapper.selectByExample(example);
        System.out.println(users);
        session.close();
    }

    /**
     * 找到年龄为null，或者年龄是10,20,30岁的用户
     */
    @Test
    public void selectByOr() {
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<Integer> ages = new ArrayList<>();
        ages.add(10);
        ages.add(20);
        ages.add(30);
        UserExample example = new UserExample();
        example.or().andAgeIsNull();
        example.or().andAgeIn(ages);
        List<User> users = userMapper.selectByExample(example);
        System.out.println(users);
        session.close();
    }

}
