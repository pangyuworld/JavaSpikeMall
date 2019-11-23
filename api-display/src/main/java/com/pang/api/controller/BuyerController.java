package com.pang.api.controller;

import com.pang.api.service.BuyerService;
import com.pang.entity.Buyer;
import com.pang.restful.ResponseEnum;
import com.pang.restful.ResponseJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author pang
 * @version V1.0
 * @ClassName: BuyerController
 * @Package com.pang.mall.controller
 * @description: 买家接口
 * @date 2019/11/11 21:24
 */
@RestController
@RequestMapping(value = "/api/buyer")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseJSON<Boolean> register(@RequestBody Buyer buyer) {
        return new ResponseJSON<>(buyerService.register(buyer), ResponseEnum.REGISTER_SUCCESS);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseJSON<Map<String, Object>> login(@RequestBody Map<String, String> map) {
        String userName = map.get("userName");
        String password = map.get("password");
        return new ResponseJSON<>(buyerService.login(userName, password), ResponseEnum.LOGIN_SUCCESS);
    }

}
