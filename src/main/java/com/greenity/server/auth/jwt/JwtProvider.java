package com.greenity.server.auth.jwt;

import com.greenity.server.global.util.redis.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key key;

    @Value("${jwt.token.access-expiration-time}")
    private long accessTokenExpTime;

    @Value("${jwt.token.refresh-expiration-time}")
    private long refreshTokenExpTime;

    private final RedisService redisService;

    public JwtProvider(@Value("${jwt.secret}") String secretKey, RedisService redisService) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.redisService = redisService;
    }

    public String generateAccessToken(long userId) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(long userId) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        redisService.saveRefreshToken(String.valueOf(userId), refreshToken, refreshTokenExpTime);

        return refreshToken;
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}