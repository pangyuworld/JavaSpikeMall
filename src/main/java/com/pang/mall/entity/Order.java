package com.pang.mall.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    /** 订单id */
    private long orderId;
    /** 下单时间 */
    private Date orderTime;
    /** 订单内的商品ID */
    private long itemId;
    /** 下单的买家ID */
    private long buyerId;
    /** 订单当前的状态 */
    @Max(value = 10, message = "订单状态异常")
    @Min(value = 0, message = "订单状态异常")
    private int orderStatus;
    /** 订单号，根据雪花算法生成 */
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
