package com.joezhou.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author JoeZhou
 */
public class JdkProxyCompany implements InvocationHandler {

    private Object customer;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws InvocationTargetException, IllegalAccessException {
        System.out.println("鉴权...");
        Object methodReturn = method.invoke(customer, args);
        System.out.println("日志...");
        return methodReturn;
    }

    /**
     * 只要调用该方法，就能成功的为指定的客户聘请一个JDK代理
     *
     * @param customer 想要聘用代理的客户
     * @return 对应客户的代理
     */
    public Object hireProxy(Object customer) {
        this.customer = customer;
        return Proxy.newProxyInstance(
                customer.getClass().getClassLoader(),
                customer.getClass().getInterfaces(), this);
    }
}