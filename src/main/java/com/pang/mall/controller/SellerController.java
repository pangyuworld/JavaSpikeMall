package com.pang.mall.controller;

import com.pang.mall.common.restful.ResponseEnum;
import com.pang.mall.common.restful.ResponseJSON;
import com.pang.mall.entity.Seller;
import com.pang.mall.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping(value = "/seller")
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
}
