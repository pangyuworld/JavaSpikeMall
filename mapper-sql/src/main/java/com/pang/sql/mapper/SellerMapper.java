package com.pang.sql.mapper;



import com.pang.entity.Seller;

import java.util.List;

/**
 * 卖家
 */
public interface SellerMapper {
    /**
     * 添加新的卖家
     *
     * @param seller 卖家信息
     */
    int addSeller(Seller seller);

    /**
     * 根据id查找卖家
     *
     * @param sellerId 卖家id
     * @return 卖家信息
     */
    Seller selectSellerById(long sellerId);

    /**
     * 根据登录名查找卖家
     *
     * @param userName 登录名
     * @return 卖家信息
     */
    Seller selectSellerByLoginName(String userName);

    /**
     * 查找所有卖家
     *
     * @return 卖家信息列表
     */
    List<Seller> selectAllSeller();

    /**
     * 更新卖家信息
     *
     * @param seller 要更新成的卖家信息
     * @return 更新成功返回1
     */
    int updateSeller(Seller seller);

    /**
     * 删除卖家信息
     *
     * @param sellerId 要删除的卖家信息
     * @return 删除成功返回1
     */
    int deleteSeller(long sellerId);
}
