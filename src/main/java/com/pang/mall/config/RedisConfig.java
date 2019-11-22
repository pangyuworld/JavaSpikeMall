package com.pang.mall.config;

import com.pang.mall.listener.RedisListener;
import com.pang.mall.listener.TestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.integration.redis.util.RedisLockRegistry;

/**
 * @author pang
 * @version V1.0
 * @ClassName: RedisConfig
 * @Package com.pang.mall.config
 * @description: redis配置
 * @date 2019/11/12 9:42
 */
@Configuration
public class RedisConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.listener}")
    private String channelName;

    @Autowired
    private RedisListener redisListener;
    @Autowired
    private TestListener testListener;

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

    /**
     * 配置RedisTemplate，解决乱码问题
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        LOGGER.debug("redis序列化配置开始");
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        // string序列化方式
        RedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        // 设置默认序列化方式
        template.setDefaultSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        LOGGER.debug("redis序列化配置结束");
        return template;
    }

    @Bean
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
        LOGGER.debug("注册一个序列化工具Bean");
        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory factory) {
        return new RedisLockRegistry(factory, "spring-cloud",60);
    }
}
