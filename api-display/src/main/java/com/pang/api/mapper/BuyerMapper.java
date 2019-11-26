package com.pang.api.mapper;

import com.pang.entity.Buyer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author pang
 * @version V1.0
 * @ClassName: BuyerMapper
 * @Package com.pang.api.mapper
 * @description:
 * @date 2019/11/23 10:43
 */
@FeignClient(value = "mapper-sql", path = "/buyer")
public interface BuyerMapper {
    /**
     * 添加新的买家信息
     *
     * @param buyer 买家信息
     * @return 添加成功返回1
     */
    @RequestMapping(method = RequestMethod.POST)
    int addBuyer(@RequestBody Buyer buyer);

    /**
     * 根据id查找买家信息
     *
     * @param buyerId 买家id
     * @return 买家信息
     */
    @RequestMapping(value = "/{buyerId}", method = RequestMethod.GET)
    Buyer selectBuyerById(@PathVariable long buyerId);

    /**
     * 根据登录名查找买家（主要用于登录）
     *
     * @param userName 登录名
     * @return 买家信息
     */
    @RequestMapping(value = "/name/{userName}", method = RequestMethod.GET)
    Buyer selectBuyerByLoginName(@PathVariable String userName);

    /**
     * 查找所有买家信息
     *
     * @return 买家信息列表
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Buyer> selectAllBuyer();

    /**
     * 更新卖家信息
     *
     * @param buyer 完整的买家信息
     * @return 更新成功返回1
     */
    @RequestMapping(method = RequestMethod.PUT)
    int updateBuyer(@RequestBody Buyer buyer);

    /**
     * 删除买家信息
     *
     * @param buyerId 要删除的买家id
     * @return 删除成功返回1
     */
    @RequestMapping(value = "/{buyerId}", method = RequestMethod.DELETE)
    int deleteBuyer(@PathVariable long buyerId);
}
