package com.joezhou.di;

import com.joezhou.di.pojo.Emp;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author JoeZhou
 */
public class EmpTest {

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

    @After
    public void after(){
        app.close();
    }
}
