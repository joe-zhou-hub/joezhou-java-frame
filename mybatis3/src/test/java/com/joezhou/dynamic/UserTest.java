package com.joezhou.dynamic;

import com.joezhou.mapper.UserMapper;
import com.joezhou.pojo.User;
import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JoeZhou
 */
public class UserTest {

    private SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-dynamic.xml");

    @Test
    public void findByIf() {
        try (SqlSession session = factory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            System.out.println(userMapper.findByIf(new User(null, null, 1, null, null)));
            System.out.println(userMapper.findByIf(new User(null, "王", null, null, null)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByWhere() {
        try (SqlSession session = factory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            System.out.println(userMapper.findByWhere(new User(null, null, null, null, null)));
            System.out.println(userMapper.findByWhere(new User(null, null, 1, null, null)));
            System.out.println(userMapper.findByWhere(new User(null, "王", null, null, null)));
            System.out.println(userMapper.findByWhere(new User(null, "王", 1, null, null)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByTrim() {
        try (SqlSession session = factory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            System.out.println(userMapper.findByTrim(new User(null, null, null, null, null)));
            System.out.println(userMapper.findByTrim(new User(null, null, 1, null, null)));
            System.out.println(userMapper.findByTrim(new User(null, "王", null, null, null)));
            System.out.println(userMapper.findByTrim(new User(null, "王", 1, null, null)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByChoose() {
        try (SqlSession session = factory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            System.out.println(userMapper.findByChoose(new User(1, "王", 1, null, null)));
            System.out.println(userMapper.findByChoose(new User(1, "王", null, null, null)));
            System.out.println(userMapper.findByChoose(new User(1, null, 1, null, null)));
            System.out.println(userMapper.findByChoose(new User(1, null, null, null, null)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findWithArray() {
        int[] ids = {3, 4, 6};
        try (SqlSession session = factory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            System.out.println(userMapper.findWithArray(ids));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findWithList() {
        List<Integer> ids = new ArrayList<>();
        ids.add(3);
        ids.add(4);
        ids.add(6);
        try (SqlSession session = factory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            System.out.println(userMapper.findWithList(ids));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findWithMap() {
        List<Integer> ids = new ArrayList<>();
        ids.add(3);
        ids.add(4);
        ids.add(6);

        Map<String, Object> map = new HashMap<>(1);
        map.put("ids", ids);
        try (SqlSession session = factory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            System.out.println(userMapper.findWithMap(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateBySet() {
        SqlSession session = factory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.updateBySet(new User(1, "赵四", null, null, null));
            userMapper.updateBySet(new User(1, null, null, 100, null));
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void updateByTrim() {
        SqlSession session = factory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.updateByTrim(new User(1, "刘能", null, null, null));
            userMapper.updateByTrim(new User(1, null, null, 0, null));
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}