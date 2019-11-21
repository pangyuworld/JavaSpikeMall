package com.pang.mall.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author pang
 * @version V1.0
 * @ClassName: RedisTool
 * @Package com.pang.mall.utils.redis
 * @description: redis工具类
 * @date 2019/11/12 8:38
 */
@Component
public class RedisTool {
    @Autowired
    private RedisTemplate redisTemplate;
    private ValueOperations redisValue;
    private ListOperations redisList;
    private HashOperations redisHash;
    private SetOperations redisSet;
    private ZSetOperations redisZSet;
    @Value("${spring.redis.listener}")
    private String channelName;

    @PostConstruct
    public void init() {
        redisValue = redisTemplate.opsForValue();
        redisList = redisTemplate.opsForList();
        redisHash = redisTemplate.opsForHash();
        redisSet = redisTemplate.opsForSet();
        redisZSet = redisTemplate.opsForZSet();
    }

    /**
     * 添加默认键值对
     *
     * @param key   键
     * @param value 值
     */
    public boolean set(String key, Object value) {
        try {
            redisValue.set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加默认键值对及设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  过期时间
     */
    public boolean set(String key, Object value, long time) {
        try {
            redisValue.set(key, value, time, TimeUnit.MILLISECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置指定key的失效时间
     *
     * @param key  键
     * @param time 失效时间
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 根据key获得过期时间
     *
     * @param key 键
     * @return 时间（毫秒），0代表永久
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键值
     */
    public boolean containsKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除key
     *
     * @param key 键
     */
    public boolean removeKey(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Arrays.asList(key));
            }
            return true;
        }
        return false;
    }

    /**
     * 获得值
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisValue.get(key);
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 递增因子
     * @return 递增后的大小
     */
    public long increment(String key, long delta) {
        if (delta <= 0) {
            throw new RuntimeException("递增因子要大于0");
        }
        return redisValue.increment(key, delta);
    }

    /**
     * 递增
     *
     * @param key 键
     * @return 递增后的大小
     */
    public long increment(String key) {
        return redisValue.increment(key);
    }

    /**
     * 递减
     *
     * @param key 键
     * @return 递减后的大小
     */
    public long decrement(String key) {
        return redisValue.decrement(key);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 递减因子
     * @return 递减后的大小
     */
    public long decrement(String key, long delta) {
        if (delta <= 0) {
            throw new RuntimeException("递增因子要大于0");
        }
        return redisValue.decrement(key, delta);
    }

    /**
     * 添加hash属性元素
     *
     * @param key   键
     * @param value 值
     */
    public boolean setHash(String key, Map<Object, Object> value) {
        try {
            redisHash.putAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加hash属性元素
     *
     * @param key   键
     * @param item  hash键
     * @param value hash值
     */
    public boolean setHash(String key, Object item, Object value) {
        try {
            redisHash.put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加hash属性元素，并设置有效时间
     *
     * @param key   键
     * @param value 值
     * @param time  有效时间
     */
    public boolean setHash(String key, Map<Object, Object> value, long time) {
        try {
            redisHash.putAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加hash属性元素，并设置有效时间
     *
     * @param key   键
     * @param item  hash键
     * @param value hash值
     * @param time  有效时间
     */
    public boolean setHash(String key, Object item, Object value, long time) {
        try {
            redisHash.put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取hash表中的某个值
     *
     * @param key  键
     * @param item hash键
     * @return hash值
     */
    public Object get(String key, Object item) {
        return redisHash.get(key, item);
    }

    /**
     * 获得整个hash表
     *
     * @param key 键
     * @return hash表
     */
    public Map<String, Object> getHash(String key) {
        return redisHash.entries(key);
    }

    /**
     * 删除hash表中的值
     *
     * @param key   键
     * @param items hash键
     */
    public boolean removeHash(String key, Object... items) {
        redisHash.delete(key, items);
        return true;
    }

    /**
     * 判断hash表中是否有hash键
     *
     * @param key  键
     * @param item hash键
     */
    public boolean containsKey(String key, Object item) {
        return redisHash.hasKey(key, item);
    }

    /**
     * 递增
     *
     * @param key   键
     * @param item  hash键
     * @param delta 递增因子
     * @return 递增后的值
     */
    public long increment(String key, Object item, long delta) {
        return redisHash.increment(key, item, delta);
    }

    /**
     * 递增
     *
     * @param key  键
     * @param item hash键
     * @return 递增后的值
     */
    public long increment(String key, Object item) {
        return increment(key, item, 1);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param item  hash键
     * @param delta 递减因子
     * @return 递减后的值
     */
    public long decrement(String key, Object item, long delta) {
        return redisHash.increment(key, item, -delta);
    }

    /**
     * 递减
     *
     * @param key  键
     * @param item hash键
     * @return 递减后的值
     */
    public long decrement(String key, Object item) {
        return decrement(key, item, 1);
    }

    /**
     * 推送消息
     *
     * @param message 消息
     */
    public void publish(Object message) {
        redisTemplate.convertAndSend(channelName, message);
    }


}
