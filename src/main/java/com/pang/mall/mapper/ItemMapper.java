package com.pang.mall.mapper;


import com.pang.mall.entity.Item;

import java.util.List;

/**
 * 商品
 */
public interface ItemMapper {
    /**
     * 添加新的商品
     *
     * @param item 商品信息
     * @return 添加成功返回1
     */
    int addItem(Item item);

    /**
     * 商品库存-1
     *
     * @param itemId 商品id
     * @return 执行成功返回1
     */
    int reduceItemCount(long itemId);

    /**
     * 获得商品库存
     *
     * @param itemId 商品ID
     * @return 商品库存
     */
    int getCount(long itemId);

    /**
     * 根据Id获取商品信息
     *
     * @param itemId 商品Id
     * @return 商品信息
     */
    Item selectItemById(long itemId);

    /**
     * 获得全部商品信息
     *
     * @return 商品信息列表
     */
    List<Item> selectAllItem();

    /**
     * 更新商品信息
     *
     * @param item 更新后的商品信息
     * @return 更新成功返回1
     */
    int updateItem(Item item);

    /**
     * 删除商品信息
     *
     * @param itemId 要删除的商品ID
     * @return 删除成功返回1
     */
    int deleteItem(long itemId);
}
