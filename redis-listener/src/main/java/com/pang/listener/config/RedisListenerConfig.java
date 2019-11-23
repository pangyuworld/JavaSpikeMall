package com.pang.listener.config;

import com.pang.listener.redis.RedisListener;
import com.pang.redis.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.integration.redis.util.RedisLockRegistry;

/**
 * @author pang
 * @version V1.0
 * @ClassName: RedisConfig
 * @Package com.pang.listener.config
 * @description:
 * @date 2019/11/23 21:59
 */
@Configuration
public class RedisListenerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisListenerConfig.class);

    @Value("${spring.redis.listener}")
    private String channelName;

    @Autowired
    private RedisListener redisListener;

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory) {
        LOGGER.debug("redis订阅监听器注册开始");
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        // 订阅通道
        container.addMessageListener(redisListener, new PatternTopic(channelName));
        LOGGER.debug("redis订阅监听器注册完毕");
        return container;
    }

    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory factory) {
        LOGGER.debug("redis注册全局锁");
        return new RedisLockRegistry(factory, "spring-cloud", 60);
    }
}
