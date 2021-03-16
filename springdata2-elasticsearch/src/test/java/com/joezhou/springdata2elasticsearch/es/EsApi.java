package com.joezhou.springdata2elasticsearch.es;

import com.joezhou.springdata2elasticsearch.elasticsearch.UserRepository;
import com.joezhou.springdata2elasticsearch.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

/**
 * @author JoeZhou
 */
@SpringBootTest
class EsApi {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        userRepository.save(new User(1, "测试姓名", "描述信息"));
    }

    @Test
    void saveAll() {
        Set<User> users = new HashSet<>();
        users.add(new User(2, "批增姓名02", "批增信息02"));
        users.add(new User(3, "批增姓名03", "批增信息03"));
        users.add(new User(4, "批增姓名04", "批增信息04"));
        users.add(new User(5, "批增姓名05", "批增信息05"));
        userRepository.saveAll(users);
    }

    @Test
    void findById(){
        User user = null;
        Optional<User> userOptional = userRepository.findById(1);
        if(userOptional.isPresent()){
            user = userOptional.get();
        }
        System.out.println(user);
    }

    @Test
    void existsById(){
        System.out.println(userRepository.existsById(1));
    }

    @Test
    void findAll() {
        Iterable<User> usersIterable = userRepository.findAll();
        usersIterable.forEach(System.out::println);
    }

    @Test
    void findAllById() {
        Set<Integer> ids = new HashSet<>();
        ids.add(1);
        ids.add(2);
        Iterable<User> usersIterable = userRepository.findAllById(ids);
        usersIterable.forEach(System.out::println);
    }

    @Test
    void count() {
        System.out.println(userRepository.count());
    }

    @Test
    void deleteById() {
        userRepository.deleteById(1);
    }

    @Test
    void deleteByEntity() {
        User user = new User();
        // 必须填写ID，其余字段可以不写
        user.setId(3);
        userRepository.delete(user);
    }

    @Test
    void deleteAllByEntity() {
        Set<User> users = new HashSet<>();
        User user11 = new User(11, null, null);
        User user12 = new User(12, null, null);
        users.add(user11);
        users.add(user12);

        // 参数需要填写id不为空的实体列表，不填表示全删
        userRepository.deleteAll(users);
    }

    @Test
    void findBySort() {
        Sort sort = Sort.by("id").descending();
        Iterable<User> usersIterable = userRepository.findAll(sort);
        usersIterable.forEach(System.out::println);
    }

    @Test
    void findByPage() {
        // p3可以省略，省略默认不排序
        Pageable page = PageRequest.of(0, 3, Sort.by("id").descending());
        if (page.isPaged()) {
            Page<User> users = userRepository.findAll(page);
            System.out.println("当前页数：" + page.getPageNumber());
            System.out.println("页面尺寸：" + page.getPageSize());
            System.out.println("总条目数：" + users.getTotalElements());
            System.out.println("总页数：" + users.getTotalPages());
            users.forEach(System.out::println);
        }
    }
}
