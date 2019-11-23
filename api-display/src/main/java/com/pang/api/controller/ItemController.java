package com.pang.api.controller;

import com.pang.api.service.ItemService;
import com.pang.entity.Item;
import com.pang.restful.ResponseEnum;
import com.pang.restful.ResponseJSON;
import com.pang.utils.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseJSON<Boolean> addItem(@RequestBody Item item, @RequestAttribute String seller) {
        Long sellerId = Long.parseLong(seller);
        item.setSellerId(sellerId);
        return new ResponseJSON<>(itemService.addItem(item), ResponseEnum.SUCCESS_OPTION);
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public ResponseJSON<Item> getItem(@PathVariable Long itemId) {
        return new ResponseJSON<>(itemService.getItemById(itemId), ResponseEnum.SUCCESS_OPTION);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseJSON<List<Item>> getItem() {
        return new ResponseJSON<>(itemService.getAllItem(), ResponseEnum.SUCCESS_OPTION);
    }
}
