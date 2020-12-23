package com.joezhou.aop.proxy;

/**
 * @author JoeZhou
 */
public class UserServiceImpl implements UserService {

    @Override
    public void insert() {
        System.out.println("insert()...");
    }

    @Override
    public void select() {
        System.out.println("select()...");
    }

    @Override
    public void update() {
        System.out.println("update()...");
    }

    @Override
    public void delete() {
        System.out.println("delete()...");
    }
}
