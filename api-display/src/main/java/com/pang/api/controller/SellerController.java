package com.pang.api.controller;

import com.pang.api.service.SellerService;
import com.pang.entity.Buyer;
import com.pang.entity.Seller;
import com.pang.restful.ResponseEnum;
import com.pang.restful.ResponseJSON;
import com.pang.utils.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author pang
 * @version V1.0
 * @ClassName: SellerController
 * @Package com.pang.mall.controller
 * @description: 卖家接口
 * @date 2019/11/11 21:24
 */
@RestController
@RequestMapping(value = "/api/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseJSON<Boolean> register(@RequestBody Seller seller) {
        return new ResponseJSON<>(sellerService.register(seller), ResponseEnum.REGISTER_SUCCESS);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseJSON<Map<String, Object>> login(@RequestBody Map<String, String> map) {
        String userName = map.get("userName");
        String password = map.get("password");
        return new ResponseJSON<>(sellerService.login(userName, password), ResponseEnum.LOGIN_SUCCESS);
    }

    @Token
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseJSON<Seller> getInfo(@RequestAttribute String seller) {
        Long sellerId = Long.parseLong(seller);
        return new ResponseJSON<>(sellerService.getSellerById(sellerId), ResponseEnum.SUCCESS_OPTION);
    }

    @Token
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseJSON<Boolean> updateBuyer(@RequestBody Seller seller) {
        return new ResponseJSON<>(sellerService.updateSeller(seller), ResponseEnum.SUCCESS_OPTION);
    }

}
