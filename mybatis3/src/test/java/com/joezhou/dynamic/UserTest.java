package com.joezhou.dynamic;

import com.joezhou.mapper.UserMapper;
import com.joezhou.pojo.User;
import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * @author JoeZhou
 */
public class UserTest {

    private SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-dynamic.xml");

    @Test
    public void findLikeNameAndGender() {
        User user = new User();
        user.setName("王");
        user.setGender(1);
        try (SqlSession session = factory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            System.out.println(userMapper.findLikeNameAndGender(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}