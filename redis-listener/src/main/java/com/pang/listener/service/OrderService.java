package com.pang.listener.service;

import com.pang.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author pang
 * @version V1.0
 * @ClassName: OrderService
 * @Package com.pang.listener.service
 * @description:
 * @date 2019/11/23 22:04
 */
@FeignClient(value = "mapper-sql", path = "/order")
public interface OrderService {
    /**
     * 添加订单
     *
     * @param order 订单信息
     * @return 添加成功返回1
     */
    @RequestMapping(method = RequestMethod.POST)
    int addOrder(@RequestBody Order order);
}
