package com.joezhou.di;

import com.joezhou.di.autowired.DogService;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author JoeZhou
 */
public class DogTest {

    private ClassPathXmlApplicationContext app;

    @Test
    public void info() {
        app = new ClassPathXmlApplicationContext("spring/di/autowired.xml");
        app.getBean(DogService.class).info();
        app.close();
    }

    @After
    public void after(){
        app.close();
    }
}
