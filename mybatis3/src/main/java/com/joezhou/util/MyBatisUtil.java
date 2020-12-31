package com.joezhou.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.Properties;

/**
 * @author JoeZhou
 */
public class MyBatisUtil {

    private static SqlSessionFactory factory;

    public static SqlSessionFactory getFactory(String resource, String env, Properties prop) {
        try {
            if (factory == null) {
                synchronized (MyBatisUtil.class) {
                    if (factory == null) {
                        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource), env, prop);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }

    public static SqlSessionFactory getFactory(String resource) {
        return getFactory(resource, null, null);
    }

    public static SqlSessionFactory getFactory(String resource, String env) {
        return getFactory(resource, env, null);
    }

    public static SqlSessionFactory getFactory(String resource, Properties prop) {
        return getFactory(resource, null, prop);
    }
}