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

    @Test
    public void insert() {
        User liuneng = new User();
        liuneng.setName("刘能");
        liuneng.setAge(50);

        User dajiao = new User();
        dajiao.setName("大脚");
        dajiao.setAge(18);

        SqlSession session = factory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.insert(liuneng);
            userMapper.insertSelective(dajiao);
            session.commit();
            System.out.println("liuneng-id:" + liuneng.getId());
            System.out.println("dajiao-id:" + dajiao.getId());
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void updateByPrimaryKey() {
        User liuneng = new User();
        liuneng.setId(10);
        liuneng.setName("刘能2");

        User dajiao = new User();
        dajiao.setId(11);
        dajiao.setName("大脚2");

        SqlSession session = factory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.updateByPrimaryKey(liuneng);
            userMapper.updateByPrimaryKeySelective(dajiao);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void updateByExample() {

        User liuneng = new User();
        liuneng.setGender(1);

        User dajiao = new User();
        dajiao.setName("大脚2");

        SqlSession session = factory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            UserExample exampleA = new UserExample();
            exampleA.createCriteria()
                    .andNameLike("赵%")
                    .andGenderNotEqualTo(0);
            userMapper.updateByExampleSelective(liuneng, exampleA);
            //userMapper.updateByPrimaryKeySelective(dajiao);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void updateByExampleSelective() {
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        UserExample example = new UserExample();
        example.createCriteria()
                .andNameEqualTo("赵老四");
        User user = new User();
        user.setName("赵国强");
        userMapper.updateByExampleSelective(user, example);
    }

    @Test
    public void deleteByPrimaryKey() {
        SqlSession session = factory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.deleteByPrimaryKey(10);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void deleteByExample() {
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        UserExample example = new UserExample();
        example.or().andAgeIsNull();
        example.or().andAgeBetween(18, 40);
        userMapper.deleteByExample(example);
    }

    @Test
    public void selectByPrimaryKey() {
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        System.out.println(userMapper.selectByPrimaryKey(7));
        session.close();
    }

    /*按条件查询：查询姓赵的男性*/
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

    /*找到年龄为null，或者年龄是10,20,30岁的用户*/
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
