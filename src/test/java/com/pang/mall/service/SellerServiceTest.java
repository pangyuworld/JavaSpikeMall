package com.pang.mall.service;

import com.pang.mall.entity.Seller;
import com.pang.mall.services.SellerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @author pang
 * @version V1.0
 * @ClassName: SellerServiceTest
 * @Package com.pang.mall.service
 * @description:
 * @date 2019/11/11 20:54
 */
@SpringBootTest
public class SellerServiceTest {

    @Autowired
    private SellerService sellerService;
    String userName="xiaopangemm";
    String password="a904237539";

    @Test
    public void registerTest(){
        Seller seller=new Seller();
        seller.setSellerName("adminTest")
                .setPassword(password)
                .setUserName(userName);
        sellerService.register(seller);
    }

    @Test
    public void loginTest(){
        Map<String, Object> adminTest = sellerService.login(userName, password);
        System.out.println(adminTest);
    }
}
