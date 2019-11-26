package com.pang.api.controller;

import com.pang.api.service.OrderService;
import com.pang.entity.Order;
import com.pang.restful.ResponseEnum;
import com.pang.restful.ResponseJSON;
import com.pang.utils.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author pang
 * @version V1.0
 * @ClassName: OrderController
 * @Package com.pang.mall.controller
 * @description: 订单控制器
 * @date 2019/11/12 13:29
 */
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Token
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseJSON<Order> addOrder(@RequestBody Order order, @RequestAttribute String buyer) {
        Long buyerId = Long.parseLong(buyer);
        order.setBuyerId(buyerId);
        return new ResponseJSON<>(orderService.addOrder(order), ResponseEnum.SUCCESS_OPTION);
    }

    @Token
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ResponseJSON<Order> getOrderByOrderNumber(Long orderNumber) {
        return new ResponseJSON<>(orderService.getOrderStatus(orderNumber), ResponseEnum.SUCCESS_OPTION);
    }

    @Token
    @RequestMapping(value = "/seller", method = RequestMethod.GET)
    public ResponseJSON<List<Map<String, Object>>> getOrderBySeller(@RequestAttribute String seller) {
        Long sellerId = Long.parseLong(seller);
        return new ResponseJSON<>(orderService.getOrderListBySeller(sellerId), ResponseEnum.SUCCESS_OPTION);
    }

    @Token
    @RequestMapping(value = "/seller/item/{itemId}", method = RequestMethod.GET)
    public ResponseJSON<List<Map<String, Object>>> getOrderBySellerAndItem(@RequestAttribute String seller, @PathVariable long itemId) {
        Long sellerId = Long.parseLong(seller);
        return new ResponseJSON<>(orderService.getOrderBySellerAndItem(sellerId, itemId), ResponseEnum.SUCCESS_OPTION);
    }

    @Token
    @RequestMapping(value = "/buyer", method = RequestMethod.GET)
    public ResponseJSON<List<Map<String, Object>>> getOrderByBuyer(@RequestAttribute String buyer) {
        Long buyerId = Long.parseLong(buyer);
        return new ResponseJSON<>(orderService.getOrderByBuyer(buyerId), ResponseEnum.SUCCESS_OPTION);
    }
}
