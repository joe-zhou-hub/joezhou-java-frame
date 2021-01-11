package com.joezhou.generator;

import com.joezhou.generator.mapper.UserMapper;
import com.joezhou.generator.pojo.User;
import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * @author JoeZhou
 */
public class UserTest {

    private SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-generator.xml");

    @Test
    public void insert() {
        User user = new User();
        user.setName("赵四3");
        user.setAge(53);
        SqlSession session = factory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.insert(user);
            session.commit();
            System.out.println("user-id:" + user.getId());
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void insertSelective() {
        User user = new User();
        user.setName("赵四4");
        user.setAge(54);
        SqlSession session = factory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.insertSelective(user);
            session.commit();
            System.out.println("user-id:" + user.getId());
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
