package com.joezhou.factory;

import com.joezhou.pojo.User;

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