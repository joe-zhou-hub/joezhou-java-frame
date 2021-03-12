package com.joezhou.springboot2jwt.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.joezhou.springboot2jwt.entity.User;

import java.util.Date;

/**
 * @author JoeZhou
 */
public class TokenUtil {

    /**
     * token令牌过期时间，单位毫秒
     */
    private static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;

    /**
     * token令牌秘钥
     */
    private static final String SECRET_KEY = "my-secret";

    /**
     * token令牌发行者
     */
    private static final String ISSUER = "JoeZhou";

    /**
     * token令牌主题
     */
    private static final String SUBJECT = "login auth";

    public static String build(User user){
        return JWT.create()
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("avatar", user.getAvatar())
                .withSubject(SUBJECT)
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public static DecodedJWT verify(String token){
        try{
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
            try {
                return jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new RuntimeException("token verify error...");
            }
        }catch (Exception e){
            return null;
        }
    }
}
