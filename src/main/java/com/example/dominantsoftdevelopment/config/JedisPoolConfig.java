package com.example.dominantsoftdevelopment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class JedisPoolConfig {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration localhost = new RedisStandaloneConfiguration("redis-10789.c239.us-east-1-2.ec2.redns.redis-cloud.com", 10789);
        localhost.setUsername("default");
        localhost.setPassword("gcXQGRg6tuDcipVxivAOPv60iZQI8Bbo");
        return new JedisConnectionFactory(localhost);
    }
}
