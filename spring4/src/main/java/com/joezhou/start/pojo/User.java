package com.joezhou.start.pojo;

/**
 * @author JoeZhou
 */
public class User {

    public User() {
        System.out.println("User()...");
    }

    public User(String name) {
        System.out.println("User(String name)... " + name);
    }

    public void init(){
        System.out.println("init()...");
    }

    public void destroy(){
        System.out.println("destroy()...");
    }
}