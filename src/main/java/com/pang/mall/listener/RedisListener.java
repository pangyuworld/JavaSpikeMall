package com.pang.mall.listener;

import com.pang.mall.entity.Order;
import com.pang.mall.entity.OrderStatus;
import com.pang.mall.services.ItemService;
import com.pang.mall.services.OrderService;
import com.pang.mall.utils.redis.RedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
    @Autowired
    private RedisTool redis;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;
    private Lock lock=new ReentrantLock();

    /**
     * 监听消息，redis收到消息以后执行的方法
     *
     * @param message 消息体
     * @param bytes   通道名
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        lock.lock();
        // 从消息队列中取出订单
        Order order = serializer.deserialize(message.getBody(), Order.class);
        // 修改订单状态
        order.setOrderStatus(OrderStatus.PROCESS_ORDER);
        // 将订单状态更新到redis中
        redis.set(String.valueOf(order.getOrderNumber()), order);
        // 下面是处理订单。。。
        // 从redis中获取缓存，如果不存在，则执行一次查数据库并将数据存到数据库
        // 按道理来说，这个执行速度是很快的，所以可以不用考虑
        Integer count = (Integer) redis.get("item.count." + order.getItemId());
        if (null == count) {
            count = itemService.getCount(order.getItemId());
            redis.set("item.count." + order.getItemId(), count);
        }
        // 这个时候已经有库存了
        if (count > 0) {
            // 如果有库存，那订单就可以正常处理
            redis.decrement("item.count." + order.getItemId());
            order.setOrderStatus(OrderStatus.PROCESS_SUCCESS);
            orderService.addOrderIntoDataBase(order);
            itemService.decreaseStock(order.getItemId());
            redis.set(String.valueOf(order.getOrderNumber()),order);
        } else {
            // 订单因为缺货然后就关闭订单
            order.setOrderStatus(OrderStatus.CLOSE_CAUSE_SOLD_OUT);
            redis.set(String.valueOf(order.getOrderNumber()),order);
        }
        lock.unlock();
    }
}
