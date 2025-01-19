package com.greenity.server.global.util.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public void saveRefreshToken(String userId, String refreshToken, long ttl) {
        redisTemplate.opsForValue().set("refreshToken:"+userId, refreshToken, ttl, TimeUnit.MILLISECONDS);
    }

    public String getRefreshToken(String userId) {
        return redisTemplate.opsForValue().get("refreshToken:"+userId);
    }

    public void deleteRefreshToken(String userId) {
        redisTemplate.delete("refreshToken:"+userId);
    }

    public void saveAuthCode(String email, String code, long ttl) {
        redisTemplate.opsForValue().set("authCode:"+email, code, ttl, TimeUnit.MILLISECONDS);
    }

    public String getAuthCode(String email) {
        return redisTemplate.opsForValue().get("authCode:"+email);
    }
}
