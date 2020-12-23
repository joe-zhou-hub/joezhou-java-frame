package com.joezhou.aop.proxy;

/**
 * @author JoeZhou
 */
public class UserServiceImpl implements UserService {

    @Override
    public void insert() {
        System.out.println("UserServiceImpl.insert()...");
    }

    @Override
    public void select() {
        System.out.println("UserServiceImpl.select()...");
    }

    @Override
    public void update() {
        System.out.println("UserServiceImpl.update()...");
    }

    @Override
    public void delete() {
        System.out.println("UserServiceImpl.delete()...");
    }
}
