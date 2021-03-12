package com.joezhou.springboot2jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.joezhou.springboot2jwt.entity.User;
import com.joezhou.springboot2jwt.util.TokenUtil;
import org.junit.jupiter.api.Test;

/**
 * @author JoeZhou
 */
class TokenUtilTest {

    @Test
    void buildToken() {
        User user  = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("123");
        user.setAvatar("admin.jpg");
        System.out.println(TokenUtil.build(user));
    }

    @Test
    void verifyToken() {
        DecodedJWT decodedJwt = TokenUtil.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKb2VaaG91IiwiaWQiOjEsImF2YXRhciI6ImFkbWluLmpwZyIsImV4cCI6MTYxNjEzNTY5MywiaWF0IjoxNjE1NTMwODkzLCJ1c2VybmFtZSI6ImFkbWluIn0.3K7KN0JFE2zV92sSyag0gp5nVzlpznAgEquiGI5fzv0\n");
        if(decodedJwt != null){
            System.out.println(decodedJwt.getClaim("id").asInt());
            System.out.println(decodedJwt.getClaim("username").asString());
            System.out.println(decodedJwt.getClaim("avatar").asString());
        }else{
            System.out.println("token验证失败...");
        }
    }
}
