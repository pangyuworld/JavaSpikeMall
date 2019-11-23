package com.pang.api.service;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author pang
 * @version V1.0
 * @ClassName: IdGenerator
 * @Package com.pang.api.clout.service
 * @description: id生成器服务
 * @date 2019/11/22 21:05
 */
@FeignClient(value = "id-generator")
public interface IdGenerator {
    @RequestMapping(value = "/id",method = RequestMethod.GET)
    Long getUniqueId();
}
