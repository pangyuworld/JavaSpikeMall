package com.pang.mall.entity;

/**
 * @author pang
 * @version V1.0
 * @ClassName: Buyer
 * @Package com.pang.mall.entity
 * @description: 买家
 * @date 2019/11/11 9:54
 */
public class Buyer {
    private long buyerId;

    private String buyerName;

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
        return "Buyer{" +
                "buyerId=" + buyerId +
                ", buyerName='" + buyerName + '\'' +
                '}';
    }
}
