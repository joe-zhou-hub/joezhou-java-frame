package com.joezhou.springboot2webflux.service;

import com.joezhou.springboot2webflux.pojo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author JoeZhou
 */
public interface UserService {
    /**
     * 通过主键查询用户记录
     *
     * @param id 主键
     * @return 满足条件的唯一记录
     */
    Mono<User> selectById(Integer id);

    /**
     * 查询全部用户记录
     *
     * @return 全部用户记录
     */
    Flux<User> selectAll();

}