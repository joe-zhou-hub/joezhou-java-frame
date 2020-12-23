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

        // cglib使用Enhancer对象创建代理
        // 需要设置父类和回调（调用哪个类的任务清单）
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(customer.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
}