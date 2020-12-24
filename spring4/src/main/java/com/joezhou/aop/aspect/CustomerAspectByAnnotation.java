package com.joezhou.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
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


}