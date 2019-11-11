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

    private String userName;

    private String password;

    public String getUserName() {
        return userName;
    }

    public Seller setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Seller setPassword(String password) {
        this.password = password;
        return this;
    }

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
