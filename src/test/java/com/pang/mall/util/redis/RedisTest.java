package com.pang.mall.util.redis;

import com.pang.mall.config.TokenConfig;
import com.pang.mall.entity.Buyer;
import com.pang.mall.utils.redis.RedisTool;
import com.pang.mall.utils.token.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    private String channelName = "mall_queue";
    @Autowired
    private RedisTool redisTool;

    @Test
    public void test() {
        Buyer buyer = new Buyer();
        buyer.setBuyerName("买家姓名");
        buyer.setUserName("登录名");
        buyer.setBuyerId(1);
        redisTool.set("user", buyer);
        Buyer b = (Buyer) redisTool.get("user");
        System.out.println(b.getUserName());
    }

    @Test
    public void publish() {
        Buyer buyer = new Buyer();
        buyer.setBuyerName("买家姓名");
        buyer.setUserName("登录名");
        buyer.setBuyerId(1);
        redisTemplate.convertAndSend(channelName, buyer);
    }

    @Test
    public void decrementTest() {
        redisTool.set("test", 10, 100);
        System.out.println(redisTool.get("test"));
        redisTool.decrement("test");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(redisTool.get("test"));
    }

    @Test
    public void addTokenTest() {
        for (int i = 0; i < 15000; i++) {
            // 向redis中保存5000个token字串作为key
            redisTool.set(TokenUtil.createJWT(i, TokenConfig.TOKEN_TTL, "1"), 1);
        }
    }

    @Test
    public void getTokenByRedis() {
        String key = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiYXVkIjoiOTk1IiwiaXNzIjoi6aKB5Y-R5Lq6IiwiZXhwIjoxNTczNjU0MjUzLCJpYXQiOjE1NzM2NDcwNTN9.t4mZHXWAGCGCUDfG7xQOfrzPxiCzhzaqZRra6IvRUnI";
        long start = System.currentTimeMillis();
        redisTool.get(key);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        // 5000 50
        // 15000 60
    }

    @Test
    public void getTokenByTokenTool() {
        String key = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiYXVkIjoiOTk1IiwiaXNzIjoi6aKB5Y-R5Lq6IiwiZXhwIjoxNTczNjU0MjUzLCJpYXQiOjE1NzM2NDcwNTN9.t4mZHXWAGCGCUDfG7xQOfrzPxiCzhzaqZRra6IvRUnI";
        long start = System.currentTimeMillis();
        TokenUtil.parseJWT(key);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        // result=80
    }

    @Test
    public void serializationTest() {
        Buyer buyer = new Buyer();
        buyer.setBuyerName("买家姓名");
        buyer.setUserName("登录名");
        buyer.setBuyerId(1);
        redisTool.set("user", buyer);
        System.out.println(redisTool.get("user") instanceof Buyer);
    }

    @Test
    public void publishWhile(){
        for (int i = 0; i < 1000000; i++) {
            redisTemplate.convertAndSend("hello","message");
        }
        while (true);
    }
}
