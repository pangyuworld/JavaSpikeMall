package com.pang.listener.redis;

import com.pang.entity.Order;
import com.pang.entity.OrderStatus;
import com.pang.listener.service.ItemService;
import com.pang.listener.service.OrderService;
import com.pang.redis.RedisTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisListener.class);

    @Autowired
    private GenericJackson2JsonRedisSerializer serializer;
    @Autowired
    private RedisTool redis;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisLockRegistry redisLockRegistry;
    @Value("${spring.redis.item-count}")
    private String itemCountName;


    /**
     * 监听消息，redis收到消息以后执行的方法
     *
     * @param message 消息体
     * @param bytes   通道名
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        long outTime = 3000;
        long value = outTime + System.currentTimeMillis();
        Lock lock = redisLockRegistry.obtain("lock");
        // 从消息队列中取出订单
        Order order = serializer.deserialize(message.getBody(), Order.class);
        Integer count = null;
        boolean allowAddOrder = false;
        boolean tryLock = false;
        try {
            tryLock = lock.tryLock(outTime, TimeUnit.MILLISECONDS);
            if (!tryLock) {
                redis.publish(order);
                return;
            }
            LOGGER.debug("持有锁,channel={}", new String(message.getChannel()));
            // 修改订单状态
            order.setOrderStatus(OrderStatus.PROCESS_ORDER);
            // 将订单状态更新到redis中
            redis.set(String.valueOf(order.getOrderNumber()), order);
            LOGGER.debug("更新订单状态到redis中,order={}", order);
            // 下面是处理订单。。。
            // 从redis中获取缓存，如果不存在，则执行一次查数据库并将数据存到数据库
            // 按道理来说，这个执行速度是很快的，所以可以不用考虑
            count = (Integer) redis.get(itemCountName + order.getItemId());
            if (null == count) {
                count = itemService.getCount(order.getItemId());
                redis.set(itemCountName + order.getItemId(), count, 1000 * 600);
                LOGGER.debug("产品库存为空，向redis写入产品库存，itemCount={},timeOut={}", order, 1000 * 20);
            } else {
                redis.expire(itemCountName + order.getItemId(), 1000 * 60);
            }
            LOGGER.debug("获取产品库存,itemCount={}", count);
            // 这个时候已经有库存了
            // 创建一个临时变量保存是否有库存
            allowAddOrder = count >= order.getOrderCount();
            // 有库存的话，库存要在同步代码块里面减少
            if (allowAddOrder) {
                redis.decrement(itemCountName + order.getItemId(), order.getOrderCount());
                LOGGER.debug("库存在redis中减少1，key={}，value={}", itemCountName + order.getItemId(), count - order.getOrderCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 解锁，下面进行判断
            if (tryLock) {
                LOGGER.debug("解锁，channel={},value={}", new String(message.getChannel()), value);
                lock.unlock();
            }

        }
        // 如果库存大于要购买的数量
        if (allowAddOrder) {
            LOGGER.warn("有库存，订单正常受理，count={},order={}", count, order);
            // 如果有库存，那订单就可以正常处理
            boolean addOrderStatus = orderService.addOrder(order)>0;
            boolean reduceStatus = itemService.reduceItemCount(order.getItemId(), order.getOrderCount())>0;
            if (addOrderStatus && reduceStatus) {
                order.setOrderStatus(OrderStatus.PROCESS_SUCCESS);
            } else {
                LOGGER.warn("数据库操作失败，失败原因addOrderStatus={},reduceStatus={}", addOrderStatus, reduceStatus);
            }
            redis.set(String.valueOf(order.getOrderNumber()), order);
            LOGGER.debug("订单处理完毕，并更新到redis,order={}", order);

        } else {
            // 订单因为缺货然后就关闭订单
            order.setOrderStatus(OrderStatus.CLOSE_CAUSE_SOLD_OUT);
            redis.set(String.valueOf(order.getOrderNumber()), order);
            LOGGER.warn("订单因为没有库存而关闭,count={},order={}", count, order);
        }
    }

}
