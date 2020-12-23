package com.joezhou.di;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author JoeZhou
 */
public class ManagerTest {

    private ClassPathXmlApplicationContext app;

    @Test
    public void constructor() {
        app = new ClassPathXmlApplicationContext("spring/di/constructor.xml");
    }

    @After
    public void after(){
        app.close();
    }
}
