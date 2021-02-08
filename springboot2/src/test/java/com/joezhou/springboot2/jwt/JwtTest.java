package com.joezhou.springboot2.jwt;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

/**
 * @author JoeZhou
 */
class JwtTest {

    @Test
    void geneJwt() {
        System.out.println(
                JwtUtil.geneToken(new User(1, "admin", "123", "admin.jpg")));
    }

    @Test
    void checkJwt() {
        // 将上一个方法生成的token拿过来
        // 这个字符串稍微改动一点都会返回一个null的Claims
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImF2YXRhciI6ImFkbWluLmpwZyIsInN1YiI6IkpvZVpob3UiLCJpYXQiOjE2MTI3NDczNzMsImV4cCI6MTYxMjgzMzc3M30.INl0HMyxwWpLtd9mhW9yB9PlLQLUHKm57UfeYVzNdIg";
        Claims claims = JwtUtil.checkToken(token);
        if (claims != null) {
            System.out.println(claims.get("id"));
            System.out.println(claims.get("username"));
            System.out.println(claims.get("avatar"));
        } else {
            System.out.println("illegal token...");
        }
    }
}