package com.joezhou.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author JoeZhou
 */
@Aspect
@Component
public class CustomerAspectByAnnotation {

    @Pointcut("execution(* com.joezhou.aop.aspect.CustomerService.delete(..))")
    public void deletePointCut() {
    }

    @Pointcut("execution(* com.joezhou.aop.aspect.CustomerService.select(..))")
    public void selectPointCut() {
    }

    @Pointcut("execution(* com.joezhou.aop.aspect.CustomerService.update(..))")
    public void updatePointCut() {
    }

    @Pointcut("execution(* com.joezhou.aop.aspect.CustomerService.insert(..))")
    public void insertPointCut() {
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

    @AfterThrowing(pointcut = "updatePointCut()&&args(user)", throwing = "e", argNames = "user,e")
    public void afterThrowingAdvice(Map<String, Object> user, Exception e) {
        System.out.println("afterThrowingAdvice(): 爆发异常...");
        System.out.println("\tparams: " + user);
        System.out.println("\texception: " + e);
    }

    @SuppressWarnings("unchecked")
    @Around("insertPointCut()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        List<Integer> list = null;
        System.out.println("aroundAdvice(): 环绕开始...");
        Object[] args = pjp.getArgs();
        System.out.println("\tparams: " + Arrays.toString(args));
        try {
            System.out.println("aroundAdvice(): 鉴权...");
            list = (List<Integer>) pjp.proceed(args);
        } catch (Throwable e) {
            System.out.println("aroundAdvice(): 爆发异常...");
            System.out.println("\texception: " + e);
        } finally {
            System.out.println("aroundAdvice(): 打印日志...");
            System.out.println("aroundAdvice(): 将原返回值 [1,2,3] 改为 [4,5,6]...");
            if (list != null) {
                list.clear();
                list.add(4);
                list.add(5);
                list.add(6);
            }
        }
        return list;
    }

}