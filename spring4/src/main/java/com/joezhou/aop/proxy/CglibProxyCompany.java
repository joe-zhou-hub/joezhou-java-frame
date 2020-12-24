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

    /**
     * 代理任务清单
     *
     * @param o           代理对象
     * @param method      代理方法的Method对象
     * @param objects     代理方法的形参
     * @param methodProxy 方法的代理对象
     * @return 代理方法的返回值，若是 `void`，就返回 `null`
     */
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