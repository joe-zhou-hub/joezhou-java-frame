package com.joezhou.ioc;

import com.joezhou.pojo.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author JoeZhou
 */
public class IocTest {

    private ClassPathXmlApplicationContext app;

    @Test
    public void buildByConstructor() {
        app = new ClassPathXmlApplicationContext("spring/ioc/build-by-constructor.xml");
        app.getBean(User.class);
        app.close();
    }

    @Test
    public void buildByStaticFactory() {
        app = new ClassPathXmlApplicationContext("spring/ioc/build-by-static-factory.xml");
        app.getBean(User.class);
        app.close();
    }

    @Test
    public void buildByDynamicFactory() {
        app = new ClassPathXmlApplicationContext("spring/ioc/build-by-dynamic-factory.xml");
        app.getBean(User.class);
        app.close();
    }

    @Test
    public void lazy() {
        app = new ClassPathXmlApplicationContext("spring/ioc/lazy.xml");
        app.getBean(User.class);
        app.close();
    }

    @Test
    public void scope() {
        app = new ClassPathXmlApplicationContext("spring/ioc/scope.xml");
        User userA = app.getBean(User.class);
        User userB = app.getBean(User.class);
        System.out.println(userA.hashCode());
        System.out.println(userB.hashCode());
        app.close();
    }

    @Test
    public void lifeCycle() {
        app = new ClassPathXmlApplicationContext("spring/ioc/life-cycle.xml");
        app.getBean(User.class);
        app.close();
    }

}
