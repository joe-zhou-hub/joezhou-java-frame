package com.joezhou.aop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author JoeZhou
 */
@Aspect
@Component
public class CustomerAspectByAnnotation {

    @Pointcut("execution(public * com.joezhou.aop.aspect..*.*(..))")
    public void pointCutA() {
    }

    @Before(value = "pointCutA()&&args(name, id)", argNames = "name,id")
    public void beforeAdvice(String name, Integer id) {
        System.out.println("beforeAdvice(): 鉴权...");
        System.out.println("\tparams: " + name + " : " + id);
    }

    @After("pointCutA()")
    public void afterAdvice() {
        System.out.println("afterAdvice(): 记录日志...");
    }


}