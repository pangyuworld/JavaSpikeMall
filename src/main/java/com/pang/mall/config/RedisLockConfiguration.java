package com.pang.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

/**
 * @author pang
 * @version V1.0
 * @ClassName: RedissionConfig
 * @Package com.pang.mall.config
 * @description:
 * @date 2019/11/19 20:39
 */
@Configuration
public class RedisLockConfiguration {

    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory factory){
        return new RedisLockRegistry(factory, "spring-cloud");
    }

}
