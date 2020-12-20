package com.joezhou.start;

import com.joezhou.pojo.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author JoeZhou
 */
public class StartTest {

    private ClassPathXmlApplicationContext app;

    @Test
    public void classPathApp() {
        // "classpath:" Can be omitted
        app = new ClassPathXmlApplicationContext("classpath:spring/start/app-classpath.xml");
        Student student = (Student) app.getBean("student");
        student.setName("赵四");
        System.out.println(student.getName());
        app.close();
    }

    @Test
    public void fileSystemApp() {
        FileSystemXmlApplicationContext app;
        app = new FileSystemXmlApplicationContext("app-file-system.xml");
        Student studentE = (Student) app.getBean("studentE");
        studentE.setName("studentE");
        System.out.println(studentE.getName());
        app.close();
    }

    @Test
    public void loadMultipleXmlByArray() {
        app = new ClassPathXmlApplicationContext("spring/start/multiple-a.xml", "spring/start/multiple-b.xml");
        Student studentA = (Student) app.getBean("studentA");
        Student studentB = (Student) app.getBean("studentB");
        studentA.setName("studentA");
        System.out.println(studentA.getName());
        studentB.setName("studentB");
        System.out.println(studentB.getName());
        app.close();
    }

    @Test
    public void loadMultipleXmlByImport() {
        app = new ClassPathXmlApplicationContext("spring/start/multiple-c.xml");
        Student studentC = (Student) app.getBean("studentC");
        Student studentD = (Student) app.getBean("studentD");
        studentC.setName("studentC");
        System.out.println(studentC.getName());
        studentD.setName("studentD");
        System.out.println(studentD.getName());
        app.close();
    }

    @Test
    public void getBeanById() {
        app = new ClassPathXmlApplicationContext("spring/start/get-by-id.xml");
        Student student = (Student) app.getBean("studentF");
        student.setName("studentF");
        System.out.println(student.getName());
        app.close();
    }

    @Test
    public void getBeanByName() {
        app = new ClassPathXmlApplicationContext("spring/start/get-by-name.xml");
        Student student = (Student) app.getBean("admin");
        student.setName("admin");
        System.out.println(student.getName());
        app.close();
    }

    @Test
    public void getBeanByClass() {
        app = new ClassPathXmlApplicationContext("spring/start/get-by-class.xml");
        Student student = app.getBean(Student.class);
        student.setName("student");
        System.out.println(student.getName());
        app.close();
    }
}
