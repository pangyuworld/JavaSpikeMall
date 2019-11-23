package com.pang.sql.controller;

import com.pang.entity.Buyer;
import com.pang.sql.mapper.BuyerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pang
 * @version V1.0
 * @ClassName: BuyerController
 * @Package com.pang.sql.controller
 * @description: 买家接口
 * @date 2019/11/23 10:35
 */
@RestController
@Configuration
public class BuyerController {



    @Autowired
    private BuyerMapper mapper;

    @RequestMapping(value = "/buyer", method = RequestMethod.POST)
    public boolean addBuyer(Buyer buyer) {
        return mapper.addBuyer(buyer) > 0;
    }

    @RequestMapping(value = "/buyer/{buyerId}", method = RequestMethod.GET)
    public Buyer getBuyer(@PathVariable Long buyerId) {
        return mapper.selectBuyerById(buyerId);
    }

    @RequestMapping(value = "/buyer", method = RequestMethod.GET)
    public List<Buyer> getBuyerList() {
        return mapper.selectAllBuyer();
    }

    @RequestMapping(value = "/buyer", method = RequestMethod.PUT)
    public boolean updateBuyer(Buyer buyer) {
        return mapper.updateBuyer(buyer) > 0;
    }

    @RequestMapping(value = "/buyer", method = RequestMethod.DELETE)
    public boolean deleteBuyer(Long buyerId) {
        return mapper.deleteBuyer(buyerId) > 0;
    }
}
