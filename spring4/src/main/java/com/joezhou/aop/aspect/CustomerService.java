package com.joezhou.aop.aspect;

import org.springframework.stereotype.Service;

/**
 * @author JoeZhou
 */
@Service
public class CustomerService {

    public void delete(String name, Integer id) {
        System.out.println("CustomerService: delete " + name + ", id is " + id);
    }
}