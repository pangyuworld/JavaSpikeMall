package com.pang.sql.mapper;


import com.pang.entity.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 订单
 */
@RestController
@RequestMapping("/order")
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

    /**
     * 查询商家全部订单
     *
     * @param sellerId 商家ID
     * @return 订单列表
     */
    @RequestMapping(value = "/seller/{sellerId}", method = RequestMethod.GET)
    List<Map<String, Object>> getOrderBySeller(@RequestParam @PathVariable long sellerId);

    /**
     * 查找商家指定商品的订单
     *
     * @param sellerId 商家ID
     * @param itemId   商品id
     * @return 订单列表
     */
    @RequestMapping(value = "/seller/{sellerId}/item/{itemId}", method = RequestMethod.GET)
    List<Map<String, Object>> getOrderBySellerAndItem(@RequestParam @PathVariable long sellerId, @RequestParam @PathVariable long itemId);

    /**
     * 查找卖家全部订单信息
     *
     * @param buyerId 买家ID
     * @return 订单列表
     */
    @RequestMapping(value = "/buyer/{buyerId}", method = RequestMethod.GET)
    List<Map<String, Object>> getOrderByBuyer(@RequestParam @PathVariable long buyerId);
}
