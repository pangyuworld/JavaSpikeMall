package com.pang.entity;

import java.util.StringJoiner;

/**
 * @author pang
 * @version V1.0
 * @ClassName: Buyer
 * @Package com.pang.mall.entity
 * @description: 买家
 * @date 2019/11/11 9:54
 */
public class Buyer {
    /** 买家ID */
    private long buyerId;
    /** 买家姓名 */
    private String buyerName;
    /** 买家登录名 */
    private String userName;
    /** 买家登录密码 */
    private String password;

    public String getUserName() {
        return userName;
    }

    public Buyer setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Buyer setPassword(String password) {
        this.password = password;
        return this;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public Buyer setBuyerId(long buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public Buyer setBuyerName(String buyerName) {
        this.buyerName = buyerName;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Buyer.class.getSimpleName() + "[", "]")
                .add("buyerId=" + buyerId)
                .add("buyerName='" + buyerName + "'")
                .add("userName='" + userName + "'")
                .add("password='" + password + "'")
                .toString();
    }
}
