package com.pang.mall.mapper;

import com.pang.mall.entity.Buyer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

/**
 * @author pang
 * @version V1.0
 * @ClassName: BuyerMapperTest
 * @Package com.pang.mall.mapper
 * @description:
 * @date 2019/11/11 10:46
 */
@SpringBootTest
public class BuyerMapperTest {
    @Autowired
    private BuyerMapper buyerMapper;
    private Buyer buyer;
    public void init() {
        buyer = new Buyer();
        buyer.setBuyerId(2);
        buyer.setBuyerName("测试姓名");
    }

    @Test
    public void addBuyerTest() {
        init();
        int result = buyerMapper.addBuyer(buyer);
        Assertions.assertEquals(result, 1);
    }
    @Test
    public void selectBuyerByIdTest(){
        init();
        Buyer buyer=buyerMapper.selectBuyerById(2);
        Assertions.assertEquals(this.buyer.toString(),buyer.toString());
    }
}
