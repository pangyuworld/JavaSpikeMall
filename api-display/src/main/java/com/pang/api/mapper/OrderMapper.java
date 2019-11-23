package com.pang.api.mapper;


import com.pang.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单
 */
@FeignClient(value = "mapper-sql", path = "/order")
public interface OrderMapper {
    /**
     * 添加订单
     *
     * @param order 订单信息
     * @return 添加成功返回1
     */
    @RequestMapping(method = RequestMethod.POST)
    int addOrder(@RequestBody Order order);

    /**
     * 根据ID获取订单信息
     *
     * @param orderId 订单Id
     * @return 订单信息
     */
    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    Order selectOrderById(@PathVariable long orderId);

    /**
     * 根据订单号获取订单信息
     *
     * @param orderNumber 订单号
     * @return 订单信息
     */
    @RequestMapping(value = "/number/{orderNumber}", method = RequestMethod.GET)
    Order selectOrderByOrderNumber(@PathVariable long orderNumber);

    /**
     * 获取全部订单信息
     *
     * @return 订单信息列表
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Order> selectAllOrder();

    /**
     * 更新订单信息，主要是更新订单状态
     *
     * @param order 更新后的订单信息
     * @return 更新成功返回1
     */
    @RequestMapping(method = RequestMethod.PUT)
    int updateOrder(@RequestBody Order order);

    /**
     * 删除订单信息
     *
     * @param order 要删除的订单id
     * @return 删除成功返回1
     */
    @RequestMapping(method = RequestMethod.DELETE)
    int deleteOrder(@RequestBody Order order);
}
