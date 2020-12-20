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

    @Test
    public void multipleByConstructor() {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("spring/start/app-multiple-a.xml", "spring/start/app-multiple-b.xml");
        Student studentA = (Student) app.getBean("studentA");
        Student studentB = (Student) app.getBean("studentB");
        studentA.setName("studentA");
        System.out.println(studentA.getName());
        studentB.setName("studentB");
        System.out.println(studentB.getName());
        app.close();
    }

    @Test
    public void multipleByImport() {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("spring/start/app-multiple-c.xml");
        Student studentC = (Student) app.getBean("studentC");
        Student studentD = (Student) app.getBean("studentD");
        studentC.setName("studentC");
        System.out.println(studentC.getName());
        studentD.setName("studentD");
        System.out.println(studentD.getName());
        app.close();
    }
}
