package com.bothsavage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(200);
        jedisPoolConfig.setMaxIdle(50);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setTestOnBorrow(true);
        return new JedisPool(jedisPoolConfig,"127.0.0.1");
    }
}
