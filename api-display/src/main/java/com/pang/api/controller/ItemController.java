package com.pang.api.controller;

import com.pang.api.service.ItemService;
import com.pang.entity.Item;
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
 * @ClassName: ItemController
 * @Package com.pang.mall.controller
 * @description: 商品控制器
 * @date 2019/11/12 11:40
 */
@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Token
    @RequestMapping(method = RequestMethod.POST)
    public ResponseJSON<Boolean> addItem(@RequestBody Item item, @RequestAttribute String seller) {
        Long sellerId = Long.parseLong(seller);
        item.setSellerId(sellerId);
        return new ResponseJSON<>(itemService.addItem(item), ResponseEnum.SUCCESS_OPTION);
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public ResponseJSON<Map<String, Object>> getItem(@PathVariable Long itemId) {
        return new ResponseJSON<>(itemService.getItemById(itemId), ResponseEnum.SUCCESS_OPTION);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseJSON<List<Map<String, Object>>> getItem() {
        return new ResponseJSON<>(itemService.getAllItem(), ResponseEnum.SUCCESS_OPTION);
    }

    @Token
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseJSON<Boolean> updateItem(@RequestBody Item item, @RequestAttribute String seller) {
        Long sellerId = Long.parseLong(seller);
        item.setSellerId(sellerId);
        return new ResponseJSON<>(itemService.updateItem(item), ResponseEnum.SUCCESS_OPTION);
    }

    @Token
    @RequestMapping(value = "/confirm/{sellerId}", method = RequestMethod.POST)
    public ResponseJSON<Boolean> confirm(@PathVariable long sellerId, @RequestAttribute String seller) {
        boolean result = Long.parseLong(seller) == sellerId;
        return new ResponseJSON<>(result, ResponseEnum.SUCCESS_OPTION);
    }

    @RequestMapping(value = "/seller/{sellerId}", method = RequestMethod.GET)
    public ResponseJSON<List<Map<String, Object>>> getItemBySeller(@PathVariable long sellerId) {
        return new ResponseJSON<>(itemService.getItemBySeller(sellerId), ResponseEnum.SUCCESS_OPTION);
    }
}
