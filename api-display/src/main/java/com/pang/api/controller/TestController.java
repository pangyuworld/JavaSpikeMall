package com.pang.api.controller;

import com.pang.api.clout.service.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pang
 * @version V1.0
 * @ClassName: TestController
 * @Package com.pang.api.controller
 * @description: 测试控制器
 * @date 2019/11/22 20:43
 */
@RestController
public class TestController {
    @Autowired
    private IdGenerator idGenerator;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getStr(){
        long id=idGenerator.getUniqueId();
        return ""+id;
    }
}
