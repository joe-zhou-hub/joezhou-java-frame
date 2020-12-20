package com.joezhou.start;

import com.joezhou.pojo.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author JoeZhou
 */
public class AppStartTest {

    @Test
    public void start() {
        // "classpath:" Can be omitted
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/start/app-start.xml");
        Student student = (Student) app.getBean("student");
        student.setName("赵四");
        System.out.println(student.getName());
        app.close();
    }
}
