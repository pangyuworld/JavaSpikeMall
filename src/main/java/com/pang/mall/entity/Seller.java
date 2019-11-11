package com.pang.mall.entity;

/**
 * @author pang
 * @version V1.0
 * @ClassName: Seller
 * @Package com.pang.mall.entity
 * @description: 卖家类
 * @date 2019/11/11 9:40
 */
public class Seller {

    private long sellerId;

    private String sellerName;


    public String getSellerName() {
        return sellerName;
    }

    public Seller setSellerName(String sellerName) {
        this.sellerName = sellerName;
        return this;
    }

    public long getSellerId() {
        return sellerId;
    }

    public Seller setSellerId(long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sellerId=" + sellerId +
                ", sellerName='" + sellerName + '\'' +
                '}';
    }
}
