package com.pang.mall.util.redis;

import com.pang.mall.entity.Buyer;
import com.pang.mall.utils.redis.RedisTool;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DefaultMessage;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author pang
 * @version V1.0
 * @ClassName: RedisTest
 * @Package com.pang.mall.util.redis
 * @description:
 * @date 2019/11/12 8:35
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    private String channelName="mall_queue";
    @Autowired
    private RedisTool redisTool;

    @Test
    public void test(){
        Buyer buyer=new Buyer();
        buyer.setBuyerName("买家姓名");
        buyer.setUserName("登录名");
        buyer.setBuyerId(1);
        redisTool.set("user",buyer);
        Buyer b= (Buyer) redisTool.get("user");
        System.out.println(b.getUserName());
    }

    @Test
    public void publish(){
        Buyer buyer=new Buyer();
        buyer.setBuyerName("买家姓名");
        buyer.setUserName("登录名");
        buyer.setBuyerId(1);
        redisTemplate.convertAndSend(channelName,buyer);
    }

    @Test
    public void decrementTest(){
        redisTool.set("test",10);
        System.out.println(redisTool.get("test"));
        redisTool.decrement("test");
        System.out.println(redisTool.get("test"));
    }
}
