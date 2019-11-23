package com.pang.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author pang
 * @version V1.0
 * @ClassName: TokenConfig
 * @Package com.pang.mall.config
 * @description: token配置类
 * @date 2019/11/11 21:06
 */
@Component
public class TokenConfig {
    public static long TOKEN_TTL;

    @Value("${token.ttl}")
    private long tokenTtl;

    @PostConstruct
    public void setTokenTtl() {
        // TOKEN_TTL = Long.parseLong(tokenTtl);
        TOKEN_TTL = tokenTtl;
    }

}
