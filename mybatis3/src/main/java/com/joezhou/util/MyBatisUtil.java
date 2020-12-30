package com.joezhou.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author JoeZhou
 */
public class MyBatisUtil {

    private static SqlSessionFactory factory;

    public static SqlSessionFactory getFactory(String resource) {
        try {
            if (factory == null) {
                synchronized (MyBatisUtil.class) {
                    if (factory == null) {
                        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }
}