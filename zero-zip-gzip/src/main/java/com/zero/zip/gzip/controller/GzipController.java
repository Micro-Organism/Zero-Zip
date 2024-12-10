package com.zero.zip.gzip.controller;

import com.zero.zip.gzip.domain.entity.SystemUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/gzip")
public class GzipController {

    private final RedisTemplate redisTemplateWithJackson;

    @Autowired
    public GzipController(RedisTemplate redisTemplateWithJackson) {
        this.redisTemplateWithJackson = redisTemplateWithJackson;
    }

    @PostMapping("/hello")
    public SystemUserEntity showHelloWorld(@RequestBody SystemUserEntity user) {
        log.info("showHelloWorld => user:{}", user);
        return user;
    }

    @PostMapping("/redis")
    public SystemUserEntity redis(@RequestBody SystemUserEntity user) {
        log.info("redis => user:{}", user);
        redisTemplateWithJackson.opsForValue().set("user", user);
        return (SystemUserEntity) redisTemplateWithJackson.opsForValue().get("user");
    }
}
