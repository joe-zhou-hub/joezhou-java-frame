package com.joezhou.springboot2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoeZhou
 */
@Aspect
@Configuration
public class AopAspect {

    @Around("execution(* com.joezhou.springboot2.aop..*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("aop: before...");
        Object result = null;
        try {
            // 放行
            result = pjp.proceed(pjp.getArgs());
            System.out.println("aop: after-returning...");
        } catch (Throwable e) {
            System.out.println("aop: throwing...");
        }

        System.out.println("aop: after...");
        return result;
    }
}