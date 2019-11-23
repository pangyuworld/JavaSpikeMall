package com.pang.entity;

/**
 * @author pang
 * @version V1.0
 * @ClassName: OrderStatus
 * @Package com.pang.mall.entity
 * @description: 订单状态
 * @date 2019/11/12 10:38
 */
public abstract class OrderStatus {
    /** 创建订单 */
    public static final int CREATED_ORDER = 0;
    /** 处理订单 */
    public static final int PROCESS_ORDER = 1;
    /** 处理完成 */
    public static final int PROCESS_SUCCESS = 2;
    /** 订单关闭 */
    public static final int CLOSE_ORDER = 3;
    /** 订单因为无货导致关闭 */
    public static final int CLOSE_CAUSE_SOLD_OUT = 9;
}
