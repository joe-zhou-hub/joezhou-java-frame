package com.joezhou.start;

import com.joezhou.pojo.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author JoeZhou
 */
public class StartTest {

    private ClassPathXmlApplicationContext classPathApp;
    private FileSystemXmlApplicationContext fileSystemApp;

    @Test
    public void start() {
        // "classpath:" Can be omitted
        classPathApp = new ClassPathXmlApplicationContext("classpath:spring/start/app-start.xml");
        Student student = (Student) classPathApp.getBean("student");
        student.setName("赵四");
        System.out.println(student.getName());
        classPathApp.close();
    }

    @Test
    public void loadMultipleXmlByArray() {
        classPathApp = new ClassPathXmlApplicationContext("spring/start/app-multiple-a.xml", "spring/start/app-multiple-b.xml");
        Student studentA = (Student) classPathApp.getBean("studentA");
        Student studentB = (Student) classPathApp.getBean("studentB");
        studentA.setName("studentA");
        System.out.println(studentA.getName());
        studentB.setName("studentB");
        System.out.println(studentB.getName());
        classPathApp.close();
    }

    @Test
    public void loadMultipleXmlByImport() {
        classPathApp = new ClassPathXmlApplicationContext("spring/start/app-multiple-c.xml");
        Student studentC = (Student) classPathApp.getBean("studentC");
        Student studentD = (Student) classPathApp.getBean("studentD");
        studentC.setName("studentC");
        System.out.println(studentC.getName());
        studentD.setName("studentD");
        System.out.println(studentD.getName());
        classPathApp.close();
    }

    @Test
    public void fileSystem() {
        fileSystemApp = new FileSystemXmlApplicationContext("app-file-system.xml");
        Student studentE = (Student)fileSystemApp.getBean("studentE");
        studentE.setName("studentE");
        System.out.println(studentE.getName());
        fileSystemApp.close();
    }
}
