package com.joezhou.di;

import com.joezhou.resource.DeptService;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author JoeZhou
 */
public class DeptTest {

    private ClassPathXmlApplicationContext app;

    @Test
    public void info() {
        app = new ClassPathXmlApplicationContext("spring/di/dept.xml");
        app.getBean(DeptService.class).info();
    }

    @After
    public void after(){
        app.close();
    }
}
