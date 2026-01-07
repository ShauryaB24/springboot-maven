package com.shaurya.rest.webservices.restful_web_services.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testSendMail() {
        redisTemplate.opsForValue().set("email", "shaurya@email.com");
        Object salary = redisTemplate.opsForValue().get("salary");
    }
}
