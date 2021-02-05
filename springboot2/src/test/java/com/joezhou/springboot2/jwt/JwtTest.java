package com.joezhou.springboot2.jwt;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

/**
 * @author JoeZhou
 */
class JwtTest {

    @Test
    void geneJwt() {
        System.out.println(JwtUtil.geneToken(
                new User(1, "admin", "123", "admin.jpg")));
    }

    @Test
    void checkJwt() {
        // 将上一个方法生成的token拿过来
        // 这个字符串稍微改动一点都会返回一个null的Claims
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6OTk5LCJ1c2VybmFtZSI6ImpvZXpob3UiLCJhdmF0YXIiOiJqb2V6aG91LmpwZyIsInN1YiI6IkpvZVpob3UiLCJpYXQiOjE2MTI1MTU4NjYsImV4cCI6MTYxMjYwMjI2Nn0.qFtkDAzNKU19jvaGl7f9oE-qKQF2iwBtVLxdlCT7yGg";
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