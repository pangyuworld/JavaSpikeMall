package com.pang.api.controller;

import com.pang.api.common.restful.ResponseEnum;
import com.pang.api.common.restful.ResponseJSON;
import com.pang.api.mapper.BuyerMapper;
import com.pang.entity.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pang
 * @version V1.0
 * @ClassName: BuyerController
 * @Package com.pang.api.controller
 * @description:
 * @date 2019/11/23 10:46
 */
@RestController
@RequestMapping(value = "/buyer")
public class BuyerController {
    @Autowired
    private BuyerMapper buyerMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseJSON<List<Buyer>> getBuyerList() {
        return new ResponseJSON<>(buyerMapper.getBuyerList(), ResponseEnum.SUCCESS_OPTION);
    }
}
