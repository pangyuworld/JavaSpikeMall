package com.pang.entity;

import java.util.StringJoiner;

/**
 * @author pang
 * @version V1.0
 * @ClassName: Seller
 * @Package com.pang.mall.entity
 * @description: 卖家类
 * @date 2019/11/11 9:40
 */
public class Seller {
    /** 卖家ID */
    private long sellerId;
    /** 卖家姓名 */
    private String sellerName;
    /** 卖家登录名 */
    private String userName;
    /** 卖家登录密码 */
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
        return new StringJoiner(", ", Seller.class.getSimpleName() + "[", "]")
                .add("sellerId=" + sellerId)
                .add("sellerName='" + sellerName + "'")
                .add("userName='" + userName + "'")
                .add("password='" + password + "'")
                .toString();
    }
}
