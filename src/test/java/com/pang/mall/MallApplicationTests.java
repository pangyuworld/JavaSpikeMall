package com.pang.mall;

import com.pang.mall.config.TokenConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallApplicationTests {


    @Test
    void contextLoads() {
        System.out.println(TokenConfig.TOKEN_TTL);
    }

}
