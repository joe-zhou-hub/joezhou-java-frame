package com.joezhou.springboot2webflux.user;

import com.joezhou.springboot2webflux.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author JoeZhou
 */
class UserTest {

    @Test
    void webClientMono() {
        Mono<User> userMono = WebClient
                .create("http://localhost:8080/api/user/select-by-id?id=1").get()
                .retrieve().bodyToMono(User.class);
        System.out.println(userMono.block());
    }

    @Test
    void webClientFlux() {
        Flux<User> userFlux = WebClient
                .create("http://localhost:8080/api/user/select-all").get()
                .retrieve().bodyToFlux(User.class);
        System.out.println(userFlux.collectList().block());
    }
}