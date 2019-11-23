package com.pang.api.mapper;

import com.pang.entity.Buyer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
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
@FeignClient(value = "mapper-sql")
public interface BuyerMapper {
    @RequestMapping(value = "/buyer", method = RequestMethod.POST)
    boolean addBuyer(Buyer buyer);

    @RequestMapping(value = "/buyer/{buyerId}", method = RequestMethod.GET)
    Buyer getBuyer(@PathVariable Long buyerId);

    @RequestMapping(value = "/buyer", method = RequestMethod.GET)
    List<Buyer> getBuyerList();

    @RequestMapping(value = "/buyer", method = RequestMethod.PUT)
    boolean updateBuyer(Buyer buyer);

    @RequestMapping(value = "/buyer", method = RequestMethod.DELETE)
    boolean deleteBuyer(Long buyerId);
}
