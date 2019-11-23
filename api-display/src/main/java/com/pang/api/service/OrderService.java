package com.pang.api.service;

import com.pang.api.mapper.OrderMapper;
import com.pang.entity.Order;
import com.pang.entity.OrderStatus;
import com.pang.redis.RedisTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author pang
 * @version V1.0
 * @ClassName: OrderService
 * @Package com.pang.mall.services
 * @description: 订单服务
 * @date 2019/11/12 13:06
 */
@Service
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Order.class);
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RedisTool redis;
    @Autowired
    private IdGenerator idGenerator;

    /**
     * 下单
     *
     * @param order 订单信息
     * @return 包含订单信息的map，主要生成了订单号
     */
    public Order addOrder(Order order) {
        // 设置下单时间
        order.setOrderTime(new Date());
        // 设置订单号
        order.setOrderNumber(idGenerator.getUniqueId());
        // 设置当前订单状态
        order.setOrderStatus(OrderStatus.CREATED_ORDER);
        // 将订单添加到redis中
        redis.set(String.valueOf(order.getOrderNumber()), order, 1000 * 60);
        LOGGER.info("订单加入到redis中,order={}", order);
        // 将订单推送到队列中
        redis.publish(order);
        LOGGER.info("订单推送到队列中,order={}", order);
        return order;
    }

    /**
     * 查询订单状态，用于轮询
     *
     * @param orderNumber 订单号
     * @return 订单信息
     */
    public Order getOrderStatus(long orderNumber) {
        LOGGER.info("根据订单号查订单状态,orderNumber={}", orderNumber);
        Order order = (Order) redis.get(String.valueOf(orderNumber));
        LOGGER.warn("查询到存在redis中的订单信息，order={}", order);
        if (order != null) {
            // 如果订单存在
            // 订单被关闭了（无论哪种关闭情况）,则将订单从缓存中删除
            redis.removeKey(String.valueOf(order.getOrderNumber()));
            // 设置订单状态为关闭
            order.setOrderStatus(OrderStatus.CLOSE_ORDER);
            LOGGER.info("订单存在且被关闭，order={}", order);
            // 将订单状态更新到数据库
            orderMapper.updateOrder(order);
            LOGGER.debug("将订单更新到数据库，order={}", order);
            // 然后返回该订单
            return order;
        }
        // 订单如果不存在，那就是已经写入数据库了，直接从数据库查询
        order = orderMapper.selectOrderByOrderNumber(orderNumber);
        LOGGER.info("从数据库中获取到订单信息,order={}", order);
        return order;

    }

    /**
     * 真正的向数据库插入数据
     *
     * @param order 具有完整信息的订单
     */
    public boolean addOrderIntoDataBase(Order order) {
        if (orderMapper.addOrder(order) > 0) {
            LOGGER.info("向数据库插入数据成功,order={}", order);
            return true;
        }
        LOGGER.info("向数据库插入数据失败,order={}", order);
        return false;
    }
}
