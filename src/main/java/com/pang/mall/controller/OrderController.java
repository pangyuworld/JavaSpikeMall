package com.pang.mall.controller;

import com.pang.mall.common.restful.ResponseEnum;
import com.pang.mall.common.restful.ResponseJSON;
import com.pang.mall.entity.Order;
import com.pang.mall.services.OrderService;
import com.pang.mall.utils.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ResponseJSON<Order> getOrderByOrderNumber(Long orderNumber) {
        return new ResponseJSON<>(orderService.getOrderStatus(orderNumber), ResponseEnum.SUCCESS_OPTION);
    }

}
