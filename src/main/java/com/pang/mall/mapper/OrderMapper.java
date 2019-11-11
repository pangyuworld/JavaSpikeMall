package com.pang.mall.mapper;

import com.pang.mall.entity.Order;

import java.util.List;

/**
 * 订单
 */
public interface OrderMapper {
    /**
     * 添加订单
     *
     * @param order 订单信息
     * @return 添加成功返回1
     */
    int addOrder(Order order);

    /**
     * 根据ID获取订单信息
     *
     * @param orderId 订单Id
     * @return 订单信息
     */
    Order selectOrderById(long orderId);

    /**
     * 根据订单号获取订单信息
     *
     * @param orderNumber 订单号
     * @return 订单信息
     */
    Order selectOrderByOrderNumber(long orderNumber);

    /**
     * 获取全部订单信息
     *
     * @return 订单信息列表
     */
    List<Order> selectAllOrder();

    /**
     * 更新订单信息，主要是更新订单状态
     *
     * @param order 更新后的订单信息
     * @return 更新成功返回1
     */
    int updateOrder(Order order);

    /**
     * 删除订单信息
     *
     * @param order 要删除的订单id
     * @return 删除成功返回1
     */
    int deleteOrder(Order order);
}
