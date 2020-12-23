package com.joezhou.di;

import com.joezhou.di.component.CatService;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author JoeZhou
 */
public class CatTest {

    private ClassPathXmlApplicationContext app;

    @Test
    public void info() {
        app = new ClassPathXmlApplicationContext("spring/di/component.xml");
        /*((CatService)app.getBean("catService")).info();*/
        app.getBean(CatService.class).info();
    }

    @After
    public void after(){
        app.close();
    }
}
