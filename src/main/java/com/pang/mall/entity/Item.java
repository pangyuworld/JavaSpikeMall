package com.pang.mall.entity;

/**
 * @author pang
 * @version V1.0
 * @ClassName: Item
 * @Package com.pang.mall.entity
 * @description: 商品信息
 * @date 2019/11/11 10:03
 */
public class Item {
    private long itemId;
    private String itemName;
    private int itemCount;
    private long sellerId;

    public long getItemId() {
        return itemId;
    }

    public Item setItemId(long itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public Item setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public int getItemCount() {
        return itemCount;
    }

    public Item setItemCount(int itemCount) {
        this.itemCount = itemCount;
        return this;
    }

    public long getSellerId() {
        return sellerId;
    }

    public Item setSellerId(long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemCount=" + itemCount +
                ", sellerId=" + sellerId +
                '}';
    }
}
