package com.joezhou.springboot2jwt.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author JoeZhou
 */
public class JwtUtil {

    /**
     * secretKey must not be exposed
     */
    private static String secretKey = "yy06200210";

    public static String create(User user) {
        Integer id;
        String username, avatar;
        if (user == null || (id = user.getId()) == null || (username = user.getUsername()) == null || (avatar = user.getAvatar()) == null) {
            return null;
        }
        // claim: the custom kv in payload
        return Jwts.builder()
                .claim("id", id)
                .claim("username", username)
                .claim("avatar", avatar)
                .setSubject("test")
                .setIssuer("JoeZhou")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600 * 24L))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static Claims verify(String token) {
        final Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return claims;
    }
}