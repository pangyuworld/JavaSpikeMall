package com.pang.mall.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.StringJoiner;

/**
 * @author pang
 * @version V1.0
 * @ClassName: Item
 * @Package com.pang.mall.entity
 * @description: 商品信息
 * @date 2019/11/11 10:03
 */
public class Item {
    /** 商品ID */
    private long itemId;
    /** 商品名 */
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$", message = "商品名不符合规则")
    private String itemName;
    /** 商品库存 */
    @Max(value = 100000, message = "商品库存不得超过10W")
    @Min(value = 0, message = "商品库存不得低于0")
    private int itemCount;
    /** 商品价钱 */
    private double itemPrice;
    /** 商品图片 */
    private String itemImg;
    /** 商品所属商家的ID */
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

    public double getItemPrice() {
        return itemPrice;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Item.class.getSimpleName() + "[", "]")
                .add("itemId=" + itemId)
                .add("itemName='" + itemName + "'")
                .add("itemCount=" + itemCount)
                .add("itemPrice=" + itemPrice)
                .add("itemImg='" + itemImg + "'")
                .add("sellerId=" + sellerId)
                .toString();
    }

    public Item setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    public String getItemImg() {
        return itemImg;
    }

    public Item setItemImg(String itemImg) {
        this.itemImg = itemImg;
        return this;
    }

}
