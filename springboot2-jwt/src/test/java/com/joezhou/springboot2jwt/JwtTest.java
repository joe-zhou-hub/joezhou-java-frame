package com.joezhou.springboot2jwt;

import com.joezhou.springboot2jwt.jwt.JwtUtil;
import com.joezhou.springboot2jwt.jwt.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

/**
 * @author JoeZhou
 */
class JwtTest {

    @Test
    void geneJwt() {
        System.out.println(
                JwtUtil.create(new User(1, "admin", "123", "admin.jpg")));
    }

    @Test
    void checkJwt() {
        // 将上一个方法生成的token拿过来
        // 这个字符串稍微改动一点都会返回一个null的Claims
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImF2YXRhciI6ImFkbWluLmpwZyIsInN1YiI6InRlc3QiLCJpc3MiOiJKb2VaaG91IiwiaWF0IjoxNjEyNzY2ODQxLCJleHAiOjE2MTI4NTMyNDF9.EKRh_QW3lwrPPgg2zlLlTyT223Bgm4mjzWz6b-KzWrw";
        Claims claims = JwtUtil.verify(token);
        if (claims != null) {
            System.out.println(claims.get("id"));
            System.out.println(claims.get("username"));
            System.out.println(claims.get("avatar"));
        } else {
            System.out.println("illegal token...");
        }
    }
}