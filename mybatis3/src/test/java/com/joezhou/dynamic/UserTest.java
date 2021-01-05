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

    @Test
    public void findLikeNameAndGenderByIf() {
        try (SqlSession session = factory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            System.out.println(userMapper.findLikeNameAndGenderByIf(new User(null, null, 1, null, null)));
            System.out.println(userMapper.findLikeNameAndGenderByIf(new User(null, "王", null, null, null)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findLikeNameAndGenderByWhere() {
        try (SqlSession session = factory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            System.out.println(userMapper.findLikeNameAndGenderByWhere(new User(null, null, null, null, null)));
            System.out.println(userMapper.findLikeNameAndGenderByWhere(new User(null, null, 1, null, null)));
            System.out.println(userMapper.findLikeNameAndGenderByWhere(new User(null, "王", null, null, null)));
            System.out.println(userMapper.findLikeNameAndGenderByWhere(new User(null, "王", 1, null, null)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findLikeNameAndGenderByTrim() {
        try (SqlSession session = factory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            System.out.println(userMapper.findLikeNameAndGenderByTrim(new User(null, null, null, null, null)));
            System.out.println(userMapper.findLikeNameAndGenderByTrim(new User(null, null, 1, null, null)));
            System.out.println(userMapper.findLikeNameAndGenderByTrim(new User(null, "王", null, null, null)));
            System.out.println(userMapper.findLikeNameAndGenderByTrim(new User(null, "王", 1, null, null)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}