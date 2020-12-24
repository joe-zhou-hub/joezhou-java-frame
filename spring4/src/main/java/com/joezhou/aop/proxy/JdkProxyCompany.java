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

    /**
     * 代理工作清单
     *
     * @param proxy  代理对象
     * @param method 代理方法的Method对象
     * @param args   代理方法的形参
     * @return 代理方法的返回值，若是 `void`，就返回 `null`
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws InvocationTargetException, IllegalAccessException {
        System.out.println("鉴权...");
        Object methodReturn = method.invoke(customer, args);
        System.out.println("日志...");
        return methodReturn;
    }

    public Object hireProxy(Object customer) {
        this.customer = customer;
        return Proxy.newProxyInstance(
                customer.getClass().getClassLoader(),
                customer.getClass().getInterfaces(), this);
    }
}