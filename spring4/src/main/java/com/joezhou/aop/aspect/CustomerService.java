package com.joezhou.aop.aspect;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author JoeZhou
 */
@Service
public class CustomerService {

    public void delete(String name, Integer id) {
        System.out.println("CustomerService.delete()...");
        System.out.println("\tparams: " + name + ", " + id);
    }

    /**
     * 不要使用基本数据类型和final类型作为返回值测试，
     * 基本数据类型是复制传参，对复制品进行修改不会影响原来的值。
     * final类型不可被修改，也无法使用。
     */
    public List<String> select(String name) {
        System.out.println("CustomerService.select()...");
        System.out.println("\tparams: " + name);
        List<String> list = new ArrayList<>();
        list.add(name);
        return list;
    }

    public void update(Map<String, Object> user) {
        System.out.println("CustomerService.update()...");
        if (user == null) {
            throw new NullPointerException();
        }
    }

    public List<Integer> insert(Map<String, Object> user) {
        System.out.println("CustomerService.insert()...");
        if (user == null) {
            throw new NullPointerException();
        }
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        return ids;
    }
}