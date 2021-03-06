package com.joezhou.springboot2jwt.jwt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author JoeZhou
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface TokenAuth {
}