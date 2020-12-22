package com.joezhou.di;

import com.joezhou.pojo.Emp;
import com.joezhou.service.CarService;
import com.joezhou.service.CatService;
import com.joezhou.service.DeptService;
import com.joezhou.service.DogService;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author JoeZhou
 */
public class DiTest {

    private ClassPathXmlApplicationContext app;

    @Test
    public void constantField() {
        app = new ClassPathXmlApplicationContext("spring/di/constant-field.xml");
        Emp emp = app.getBean(Emp.class);
        System.out.println(emp.getName());
        System.out.println(emp.getAge());
        System.out.println(Arrays.toString(emp.getHobby()));
        System.out.println(emp.getUserList());
        System.out.println(emp.getUserSet());
        System.out.println(emp.getUserMap());
        System.out.println(emp.getJdbc());
    }

    @Test
    public void constructor() {
        app = new ClassPathXmlApplicationContext("spring/di/constructor.xml");
    }

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

    @Test
    public void deptDao() {
        app = new ClassPathXmlApplicationContext("spring/di/dept.xml");
        app.getBean(DeptService.class).info();
    }

    @Test
    public void dogDao() {
        app = new ClassPathXmlApplicationContext("spring/di/dog.xml");
        app.getBean(DogService.class).info();
        app.close();
    }

    @Test
    public void catDao() {
        app = new ClassPathXmlApplicationContext("spring/di/cat.xml");
        app.getBean(CatService.class).info();
    }

    @After
    public void after(){
        app.close();
    }
}
