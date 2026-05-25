package com.learning.spring.jwt.service;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey123456"; // 32+ chars

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                ) // 1 hour
                .signWith(key)
                .compact();
    }
}