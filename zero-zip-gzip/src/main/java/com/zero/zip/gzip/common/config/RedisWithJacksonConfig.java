package com.zero.zip.gzip.common.config;

import com.zero.zip.gzip.domain.entity.SystemUserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisWithJacksonConfig {

    @Bean(name = "redisTemplateWithJackson")
    public RedisTemplate<String, SystemUserEntity> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {

        CompressRedis compressRedis = new CompressRedis();
        //redisTemplate
        RedisTemplate<String, SystemUserEntity> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(compressRedis);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(compressRedis);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}