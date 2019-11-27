package com.pang.sql.mapper;


import com.pang.entity.Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商品
 */
@RestController
@RequestMapping("/item")
public interface ItemMapper {
    /**
     * 添加新的商品
     *
     * @param item 商品信息
     * @return 添加成功返回1
     */
    @RequestMapping(method = RequestMethod.POST)
    int addItem(@RequestBody Item item);

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

    /**
     * 根据Id获取商品信息
     *
     * @param itemId 商品Id
     * @return 商品信息
     */
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    Map<String, Object> selectItemById(@PathVariable long itemId);

    /**
     * 获得全部商品信息
     *
     * @return 商品信息列表
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Map<String, Object>> selectAllItem();

    /**
     * 更新商品信息
     *
     * @param item 更新后的商品信息
     * @return 更新成功返回1
     */
    @RequestMapping(method = RequestMethod.PUT)
    int updateItem(@RequestBody Item item);

    /**
     * 删除商品信息
     *
     * @param itemId 要删除的商品ID
     * @return 删除成功返回1
     */
    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    int deleteItem(@PathVariable long itemId);

    /**
     * 获取商家的全部商品
     *
     * @param sellerId 商家ID
     * @return 商品列表
     */
    @RequestMapping(value = "/seller/{sellerId}", method = RequestMethod.GET)
    List<Map<String, Object>> selectItemBySeller(@PathVariable long sellerId);
}
