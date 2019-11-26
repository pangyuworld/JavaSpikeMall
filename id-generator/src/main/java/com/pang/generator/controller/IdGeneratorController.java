package com.pang.generator.controller;

import com.pang.generator.util.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    private static final Logger LOGGER= LoggerFactory.getLogger(IdGeneratorController.class);

    @RequestMapping(value = "/id",method = RequestMethod.GET)
    public Long getUniqueId(@RequestParam long workId){
        long uniqueId = IdGenerator.nextId(workId);
        LOGGER.info("获取分布式唯一id,uniqueId={}",uniqueId);
        return uniqueId;
    }
}
