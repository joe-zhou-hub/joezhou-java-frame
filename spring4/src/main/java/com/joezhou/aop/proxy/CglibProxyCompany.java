package com.joezhou.aop.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author JoeZhou
 */
public class CglibProxyCompany implements MethodInterceptor {

    private Object customer;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("鉴权...");
        Object methodReturn = method.invoke(customer, objects);
        System.out.println("日志...");
        return methodReturn;
    }

    public Object hireProxy(Object customer) {
        this.customer = customer;

        // 创建一个Enhancer对象，设置父类和回调（调用哪个类的任务清单）然后创建代理
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(customer.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
}