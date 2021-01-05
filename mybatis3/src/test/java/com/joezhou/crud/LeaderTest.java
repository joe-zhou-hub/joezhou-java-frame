package com.joezhou.crud;

import com.joezhou.mapper.LeaderMapper;
import com.joezhou.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * @author JoeZhou
 */
public class LeaderTest {

    private SqlSessionFactory factory = MyBatisUtil.getFactory("mybatis-package.xml");

    @Test
    public void findById() {
        try (SqlSession session = factory.openSession()) {
            LeaderMapper leaderMapper = session.getMapper(LeaderMapper.class);
            System.out.println(leaderMapper.findById(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}