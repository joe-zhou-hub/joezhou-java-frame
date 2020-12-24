package com.joezhou.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author JoeZhou
 */
@Component
public class CustomerAspectByXml {

    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("beforeAdvice(): 鉴权...");
        System.out.println("\tparams: " + Arrays.toString(joinPoint.getArgs()));
    }

    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("afterAdvice(): 记录日志...");
    }

    public void afterReturningAdvice(JoinPoint joinPoint, List<String> list) {
        System.out.println("afterReturningAdvice(): 改变原返回值...");
        System.out.println("\tparams: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("\tpre return value: " + list);
        list.remove("zhaosi");
        list.add("liuneng");
    }

    public void afterThrowingAdvice(JoinPoint joinPoint, Exception e) {
        System.out.println("afterThrowingAdvice(): 爆发异常...");
        System.out.println("\tparams: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("\texception: " + e);
    }

    @SuppressWarnings("unchecked")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        List<Integer> list = null;
        Object[] args = pjp.getArgs();
        System.out.println("params: " + Arrays.toString(args));
        try {
            System.out.println("aroundAdvice(): 鉴权...");
            list = (List<Integer>) pjp.proceed(args);
        } catch (Throwable e) {
            System.out.println("aroundAdvice(): 爆发异常...");
            System.out.println("\texception: " + e);
        } finally {
            System.out.println("aroundAdvice(): 打印日志...");
            if (list != null) {
                list.add(4);
                list.add(5);
                list.add(6);
            }
        }
        return list;
    }
}