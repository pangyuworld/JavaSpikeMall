package com.pang.api.controller;

import com.pang.api.service.BuyerService;
import com.pang.entity.Buyer;
import com.pang.restful.ResponseEnum;
import com.pang.restful.ResponseJSON;
import com.pang.utils.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Token
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseJSON<Buyer> getBuyerInfo(@RequestAttribute String buyer) {
        Long buyerId = Long.parseLong(buyer);
        return new ResponseJSON<>(buyerService.getBuyer(buyerId), ResponseEnum.SUCCESS_OPTION);
    }

    @Token
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseJSON<Boolean> updateBuyer(@RequestBody Buyer buyer) {
        return new ResponseJSON<>(buyerService.updateBuyer(buyer), ResponseEnum.SUCCESS_OPTION);
    }
}
