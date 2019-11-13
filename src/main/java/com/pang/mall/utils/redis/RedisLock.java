package com.pang.mall.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author pang
 * @version V1.0
 * @ClassName: RedisLock
 * @Package com.pang.mall.utils.redis
 * @description: 全局锁
 * @date 2019/11/12 20:57
 */
@Component
public class RedisLock {
    @Autowired
    private RedisTool redis;
    private final String LOCK_NAME = "UUID";

    /**
     * 加锁
     *
     * @param value   当前时间+超时时间
     * @param outTime 超时时间
     */
    public boolean lock(long value, long outTime) {
        if (redis.setIfAbsent(LOCK_NAME, value, outTime)) {
            return true;
        }
        //currentValue=A   这两个线程的value都是B  其中一个线程拿到锁
        Long currentValue = (Long) (redis.get(LOCK_NAME));
        //如果锁过期
        if (!StringUtils.isEmpty(currentValue)
                && currentValue < System.currentTimeMillis()) {
            //获取上一个锁的时间
            Long oldValue = (Long) redis.getAndSet(LOCK_NAME, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     */
    public void unlock(long value) {
        try {
            Long currentValue = (Long) redis.get(LOCK_NAME);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redis.deleteKey(LOCK_NAME);
            }
        } catch (Exception e) {
            System.out.println("【redis分布式锁】解锁异常, {}");
        }
    }
}
