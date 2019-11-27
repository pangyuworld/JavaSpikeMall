package com.pang.api.service;

import com.pang.api.mapper.ItemMapper;
import com.pang.entity.Item;
import com.pang.exception.UserActionException;
import com.pang.restful.ResponseEnum;
import com.pang.utils.check.ParameterTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemMapper itemMapper;

    /**
     * 添加新的商品
     *
     * @param item 商品信息
     */
    public boolean addItem(Item item) {
        if ((item.getSellerId() <= 0)) {
            LOGGER.info("无认证信息或认证信息不正确,item={}", item);
            throw new UserActionException("无认证信息或认证信息不正确", ResponseEnum.NOT_LOGIN);
        }
        if (!ParameterTool.checkCount(item.getItemCount())) {
            LOGGER.info("库存输入出错,item={}", item);
            throw new UserActionException("库存参数输入错误", ResponseEnum.BAD_REQUEST);
        }
        if (!ParameterTool.checkPrice(item.getItemPrice())) {
            LOGGER.info("单价输入出错,item={}", item);
            throw new UserActionException("单价参数输入错误", ResponseEnum.BAD_REQUEST);
        }
        if (!ParameterTool.checkText(item.getItemName())) {
            LOGGER.info("商品名输入出错,item={}", item);
            throw new UserActionException("商品名参数输入错误", ResponseEnum.BAD_REQUEST);
        }
        if (!ParameterTool.checkText(item.getItemInfo())) {
            LOGGER.info("商品介绍输入出错,item={}", item);
            throw new UserActionException("商品介绍参数输入错误", ResponseEnum.BAD_REQUEST);
        }
        if (itemMapper.addItem(item) > 0) {
            LOGGER.debug("添加商品成功，item={}", item);
            return true;
        } else {
            LOGGER.debug("添加商品失败");
            return false;
        }
    }

    /**
     * 获得所有商品信息
     *
     * @return 商品信息列表
     */
    public List<Map<String, Object>> getAllItem() {
        LOGGER.debug("获得所有商品信息");
        return itemMapper.selectAllItem();
    }

    /**
     * 获取指定商品信息
     *
     * @param itemId 商品ID
     * @return 商品信息
     */
    public Map<String, Object> getItemById(long itemId) {
        LOGGER.debug("获得指定ID的商品信息,itemId={}", itemId);
        return itemMapper.selectItemById(itemId);
    }

    /**
     * 更新商品信息
     *
     * @param item 更新后的商品信息
     * @return 是否成功
     */
    public boolean updateItem(Item item) {
        return itemMapper.updateItem(item) > 0;
    }

    /**
     * 获取商家的全部商品
     *
     * @param sellerId 商家ID
     * @return 商品信息列表
     */
    public List<Map<String, Object>> getItemBySeller(long sellerId) {
        return itemMapper.selectItemBySeller(sellerId);
    }
}
