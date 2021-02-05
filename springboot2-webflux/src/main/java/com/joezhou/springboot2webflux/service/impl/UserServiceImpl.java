package com.joezhou.springboot2webflux.service.impl;

import com.joezhou.springboot2webflux.pojo.User;
import com.joezhou.springboot2webflux.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoeZhou
 */
@Service
public class UserServiceImpl implements UserService {

    private static List<User> users;

    static {
        users = new ArrayList<>();
        users.add(new User(1, "zhaosi"));
        users.add(new User(2, "liuneng"));
        users.add(new User(3, "dajiao"));
    }

    @Override
    public Mono<User> selectById(Integer id) {
        User user = null;
        for (User e : users) {
            if (e.getId().equals(id)) {
                user = e;
                break;
            }
        }
        return Mono.justOrEmpty(user);
    }

    @Override
    public Flux<User> selectAll() {
        return Flux.fromIterable(users);
    }
}