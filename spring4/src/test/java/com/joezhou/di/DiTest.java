package com.joezhou.di;

import com.joezhou.pojo.Emp;
import com.joezhou.service.EmpService;
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
    public void constant() {
        app = new ClassPathXmlApplicationContext("spring/di/constant.xml");
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
    public void empDaoByOuter() {
        app = new ClassPathXmlApplicationContext("spring/di/emp-outer.xml");
        app.getBean(EmpService.class).insert();
        app.close();
    }

    @Test
    public void empDaoByInner() {
        app = new ClassPathXmlApplicationContext("spring/di/emp-inner.xml");
        app.getBean(EmpService.class).insert();
        app.close();
    }

    @After
    public void after(){
        app.close();
    }
}
