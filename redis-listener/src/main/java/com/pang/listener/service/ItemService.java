package com.pang.listener.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author pang
 * @version V1.0
 * @ClassName: ItemService
 * @Package com.pang.listener.service
 * @description:
 * @date 2019/11/23 22:01
 */
@FeignClient(value = "mapper-sql", path = "/item")
public interface ItemService {
    /**
     * 商品库存-1
     *
     * @param itemId 商品id
     * @return 执行成功返回1
     */
    @RequestMapping(value = "/reduce", method = RequestMethod.PUT)
    int reduceItemCount(@RequestParam long itemId, @RequestParam int count);

    /**
     * 获得商品库存
     *
     * @param itemId 商品ID
     * @return 商品库存
     */
    @RequestMapping(value = "/{itemId}/count", method = RequestMethod.GET)
    int getCount(@PathVariable long itemId);
}
