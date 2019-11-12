package com.pang.mall.listener;

import com.pang.mall.entity.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author pang
 * @version V1.0
 * @ClassName: RedisListener
 * @Package com.pang.mall.listener
 * @description: Redis事务监听
 * @date 2019/11/12 9:38
 */
@Component
public class RedisListener implements MessageListener {

    @Autowired
    private GenericJackson2JsonRedisSerializer serializer;

    /**
     * 监听消息，redis收到消息以后执行的方法
     *
     * @param message 消息体
     * @param bytes   通道名
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        //消息体
        String body = new String(message.getBody());
        //渠道名称
        String topic = new String(bytes);
        Buyer deserialize = serializer.deserialize(message.getBody(), Buyer.class);
        System.out.println(deserialize.getUserName());
        System.out.println("消息体：" + body);
        System.out.println("渠道名称：" + topic);
    }
}
