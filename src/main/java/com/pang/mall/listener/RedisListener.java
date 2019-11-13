package com.pang.mall.listener;

import com.pang.mall.entity.Order;
import com.pang.mall.entity.OrderStatus;
import com.pang.mall.services.ItemService;
import com.pang.mall.services.OrderService;
import com.pang.mall.utils.redis.RedisLock;
import com.pang.mall.utils.redis.RedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    private RedisTool redis;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisLock lock;
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
        long outTime = 1000;
        long value = outTime + System.currentTimeMillis();
        if (!lock.lock(value, outTime)) {
            //TODO 如果没有持有锁，如何保证数据不会缺失呢？我这里采用的是将消息通信推送到消息队列中去
            //TODO 但是这种方式无法解决先到先得的业务，或许实现一个优先队列？
            redis.publish(serializer.deserialize(message.getBody(), Order.class));
            // throw new RuntimeException("错误");
            return;
        }
        // 从消息队列中取出订单
        Order order = serializer.deserialize(message.getBody(), Order.class);
        // 修改订单状态
        order.setOrderStatus(OrderStatus.PROCESS_ORDER);
        // 将订单状态更新到redis中
        redis.set(String.valueOf(order.getOrderNumber()), order);
        // 下面是处理订单。。。
        // 从redis中获取缓存，如果不存在，则执行一次查数据库并将数据存到数据库
        // 按道理来说，这个执行速度是很快的，所以可以不用考虑
        Integer count = (Integer) redis.get(itemCountName + order.getItemId());
        if (null == count) {
            count = itemService.getCount(order.getItemId());
            redis.set(itemCountName + order.getItemId(), count, 1000 * 20);
        }
        // 这个时候已经有库存了
        if (count > 0) {
            // 如果有库存，那订单就可以正常处理
            redis.decrement(itemCountName + order.getItemId());
            order.setOrderStatus(OrderStatus.PROCESS_SUCCESS);
            orderService.addOrderIntoDataBase(order);
            itemService.decreaseStock(order.getItemId());
            redis.set(String.valueOf(order.getOrderNumber()), order);
        } else {
            // 订单因为缺货然后就关闭订单
            order.setOrderStatus(OrderStatus.CLOSE_CAUSE_SOLD_OUT);
            redis.set(String.valueOf(order.getOrderNumber()), order);
        }
        // TODO 这里是测试的尝试性语句，应该在编写好客户端以后注释掉该语句
        redis.removeKey("" + order.getOrderNumber());
        // 解锁
        lock.unlock(value);
    }

}
