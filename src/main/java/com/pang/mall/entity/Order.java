package com.pang.mall.entity;

import java.util.Date;

/**
 * @author pang
 * @version V1.0
 * @ClassName: Order
 * @Package com.pang.mall.entity
 * @description: 订单类
 * @date 2019/11/11 10:26
 */
public class Order {
    private long orderId;

    private Date orderTime;

    private long itemId;

    private long buyerId;

    private int orderStatus;

    private long orderNumber;

    public long getOrderNumber() {
        return orderNumber;
    }

    public Order setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public long getOrderId() {
        return orderId;
    }

    public Order setOrderId(long orderId) {
        this.orderId = orderId;
        return this;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public Order setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public long getItemId() {
        return itemId;
    }

    public Order setItemId(long itemId) {
        this.itemId = itemId;
        return this;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public Order setBuyerId(long buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public Order setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderTime=" + orderTime +
                ", itemId=" + itemId +
                ", buyerId=" + buyerId +
                ", orderStatus=" + orderStatus +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
