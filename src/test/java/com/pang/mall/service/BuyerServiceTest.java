package com.pang.mall.service;

import com.pang.mall.entity.Buyer;
import com.pang.mall.services.BuyerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @author pang
 * @version V1.0
 * @ClassName: BuyerServiceTest
 * @Package com.pang.mall.service
 * @description:
 * @date 2019/11/11 20:54
 */
@SpringBootTest
public class BuyerServiceTest {

    @Autowired
    private BuyerService buyerService;
    String userName="xiaopangemm";
    String password="a904237539";

    @Test
    public void registerTest(){
        Buyer buyer=new Buyer();
        buyer.setBuyerName("adminTest")
                .setPassword(password)
                .setUserName(userName);
        buyerService.register(buyer);
    }

    @Test
    public void loginTest(){
        Map<String, Object> adminTest = buyerService.login(userName, password);
        System.out.println(adminTest);
    }
}
