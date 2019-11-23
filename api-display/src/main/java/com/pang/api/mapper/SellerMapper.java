package com.pang.api.mapper;


import com.pang.entity.Seller;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 卖家
 */
@FeignClient(value = "mapper-sql", path = "/seller")
public interface SellerMapper {
    /**
     * 添加新的卖家
     *
     * @param seller 卖家信息
     */
    @RequestMapping(method = RequestMethod.POST)
    int addSeller(@RequestBody Seller seller);

    /**
     * 根据id查找卖家
     *
     * @param sellerId 卖家id
     * @return 卖家信息
     */
    @RequestMapping(value = "/{sellerId}", method = RequestMethod.GET)
    Seller selectSellerById(@PathVariable long sellerId);

    /**
     * 根据登录名查找卖家
     *
     * @param userName 登录名
     * @return 卖家信息
     */
    @RequestMapping(value = "/name/{userName}", method = RequestMethod.GET)
    Seller selectSellerByLoginName(@PathVariable String userName);

    /**
     * 查找所有卖家
     *
     * @return 卖家信息列表
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Seller> selectAllSeller();

    /**
     * 更新卖家信息
     *
     * @param seller 要更新成的卖家信息
     * @return 更新成功返回1
     */
    @RequestMapping(method = RequestMethod.PUT)
    int updateSeller(@RequestBody Seller seller);

    /**
     * 删除卖家信息
     *
     * @param sellerId 要删除的卖家信息
     * @return 删除成功返回1
     */
    @RequestMapping(value = "/{sellerId}",method = RequestMethod.DELETE)
    int deleteSeller(@PathVariable long sellerId);
}
