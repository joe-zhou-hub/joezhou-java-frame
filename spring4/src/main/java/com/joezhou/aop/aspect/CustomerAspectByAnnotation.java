package com.joezhou.aop.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JoeZhou
 */
@Aspect
@Component
public class CustomerAspectByAnnotation {

    @Pointcut("execution(* com.joezhou.aop.aspect.*.delete(..))")
    public void deletePointCut() {
    }

    @Pointcut("execution(* com.joezhou.aop.aspect.*.select(..))")
    public void selectPointCut() {
    }

    @Before(value = "deletePointCut()&&args(name, id)", argNames = "name,id")
    public void beforeAdvice(String name, Integer id) {
        System.out.println("beforeAdvice(): 鉴权...");
        System.out.println("\tparams: " + name + ", " + id);
    }

    @After("deletePointCut()")
    public void afterAdvice() {
        System.out.println("afterAdvice(): 记录日志...");
    }

    @AfterReturning(value = "selectPointCut()&&args(name)", returning = "list", argNames = "name,list")
    public void afterReturningAdvice(String name, List<String> list) {
        System.out.println("afterReturningAdvice(): 改变原返回值...");
        System.out.println("\tparams: " + name);
        System.out.println("\tpre return value: " + list);
        list.remove("zhaosi");
        list.add("liuneng");
    }

}