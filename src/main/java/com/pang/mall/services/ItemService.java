package com.pang.mall.services;

import com.pang.mall.common.exception.UserActionException;
import com.pang.mall.common.restful.ResponseEnum;
import com.pang.mall.entity.Item;
import com.pang.mall.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pang
 * @version V1.0
 * @ClassName: ItemService
 * @Package com.pang.mall.services
 * @description: 商品服务
 * @date 2019/11/12 10:16
 */
@Service
public class ItemService {
    @Autowired
    private ItemMapper itemMapper;

    /**
     * 添加新的商品
     *
     * @param item 商品信息
     */
    public boolean addItem(Item item) {
        if ((item.getSellerId() <= 0)) {
            throw new UserActionException("无认证信息或认证信息不正确", ResponseEnum.NOT_LOGIN);
        }
        if (itemMapper.addItem(item) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获得所有商品信息
     *
     * @return 商品信息列表
     */
    public List<Item> getAllItem() {
        return itemMapper.selectAllItem();
    }

    /**
     * 获取指定商品信息
     *
     * @param itemId 商品ID
     * @return 商品信息
     */
    public Item getItemById(long itemId) {
        return itemMapper.selectItemById(itemId);
    }

    /**
     * 减少一个库存
     *
     * @param itemId 要减少库存的商品ID
     */
    public boolean decreaseStock(long itemId) {
        if (itemMapper.reduceItemCount(itemId) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取商品库存
     *
     * @param itemId 商品ID
     * @return 商品库存
     */
    public int getCount(long itemId) {
        return itemMapper.getCount(itemId);
    }
}
