package com.pang.generator.controller;

import com.pang.generator.util.IdGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pang
 * @version V1.0
 * @ClassName: IdGeneratorController
 * @Package com.pang.generator.controller
 * @description: id生成器控制层
 * @date 2019/11/22 20:59
 */
@RestController
public class IdGeneratorController {

    @RequestMapping(value = "/id",method = RequestMethod.GET)
    public Long generateId(){
        return IdGenerator.nextId();
    }
}
