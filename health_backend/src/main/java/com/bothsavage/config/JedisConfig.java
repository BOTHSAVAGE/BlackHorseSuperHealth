package com.bothsavage.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class JedisConfig extends CachingConfigurerSupport {


    @Bean
    public JedisPool redisPoolFactory(){

        return  new JedisPool();
    }

}