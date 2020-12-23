package com.joezhou.di;

import com.joezhou.di.bean.CarService;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author JoeZhou
 */
public class CarTest {

    private ClassPathXmlApplicationContext app;

    @Test
    public void beanOuter() {
        app = new ClassPathXmlApplicationContext("spring/di/bean-outer.xml");
        app.getBean(CarService.class).info();
    }

    @Test
    public void beanInner() {
        app = new ClassPathXmlApplicationContext("spring/di/bean-inner.xml");
        app.getBean(CarService.class).info();
    }

    @After
    public void after(){
        app.close();
    }
}
