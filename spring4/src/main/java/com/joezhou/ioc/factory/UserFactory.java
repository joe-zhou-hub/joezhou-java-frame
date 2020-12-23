package com.joezhou.ioc.factory;

import com.joezhou.start.pojo.User;

/**@author JoeZhou*/
public class UserFactory {

    public UserFactory() {
        System.out.println("UserFactory()...");
    }

    public static User getUserByStaticMethod() {
        return new User("static");
    }

    public User getUserByDynamicMethod() {
        return new User("dynamic");
    }
}