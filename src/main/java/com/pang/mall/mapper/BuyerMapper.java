package com.pang.mall.mapper;

import com.pang.mall.entity.Buyer;

import java.util.List;

/**
 * 买家
 */
public interface BuyerMapper {
    /**
     * 添加新的买家信息
     *
     * @param buyer 买家信息
     * @return 添加成功返回1
     */
    int addBuyer(Buyer buyer);

    /**
     * 根据id查找买家信息
     *
     * @param buyerId 买家id
     * @return 买家信息
     */
    Buyer selectBuyerById(long buyerId);

    /**
     * 根据登录名查找买家（主要用于登录）
     * @param userName 登录名
     * @return 买家信息
     */
    Buyer selectBuyerByLoginName(String userName);

    /**
     * 查找所有买家信息
     *
     * @return 买家信息列表
     */
    List<Buyer> selectAllBuyer();

    /**
     * 更新卖家信息
     *
     * @param buyer 完整的买家信息
     * @return 更新成功返回1
     */
    int updateBuyer(Buyer buyer);

    /**
     * 删除买家信息
     *
     * @param buyerId 要删除的买家id
     * @return 删除成功返回1
     */
    int deleteBuyer(long buyerId);
}
