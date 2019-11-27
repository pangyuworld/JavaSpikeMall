<template>
  <div>
    <Card>
      <div span="title" class="order-title">
        <h2>订单结果</h2>
        <Divider />
      </div>
      <Row type="flex" justify="space-between">
        <Col span="12">
          <img width="100%" :src="item.itemImg" />
        </Col>
        <Col span="10" class="order-info">
          商品：
          <strong>{{item.itemName}}</strong>
          <br />价格：
          <strong class="price">￥{{item.itemPrice}}</strong>
          <br />数量：
          <strong>{{order.orderCount}}</strong>
          <br />总价：
          <strong class="price">￥{{order.orderCount*item.itemPrice}}</strong>
          <br />下单人：
          <strong>{{decodeURI(buyerName)}}</strong>
          <br />
          下单时间：
          <strong>{{formatDate(order.orderTime)}}</strong>
          <br />
          订单状态：
          <strong>{{getOrderStatus(order.orderStatus)}}</strong>
        </Col>
        <Col span="1" />
      </Row>
    </Card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      item: {
        itemId: 0,
        itemName: "",
        itemCount: 0,
        itemPrice: 0,
        itemImg: "",
        sellerId: 0,
        itemInfo: ""
      },
      buyerName: ""
    };
  },
  props: {
    order: {
      orderId: 0,
      orderTime: "1970-01-00T00:00:00.000+0000",
      itemId: 0,
      buyerId: 0,
      orderStatus: 0,
      orderNumber: 0,
      orderCount: 0
    }
  },
  mounted() {
    this.getItem(this.order.itemId);
    this.getBuyer();
  },
  methods: {
    getItem(itemId) {
      this.$api.getItemById(itemId).then(res => {
        if (res.success) {
          this.item = res.data;
        } else {
          this.$Message.error(res.response.data.message);
        }
      });
    },
    getBuyer() {
      this.buyerName = this.$cookie.get("userName");
    },
    formatDate(date){
        return this.$date.formatDate(new Date(date))
    },
    getOrderStatus(statusCode){
        switch(statusCode) {
            case '0':return "订单已被创建，但未受理";break;
            case '1':return "订单正在受理";break;
            case '2':return "订单处理完成";break;
            case '3':return "订单处理完成并正常关闭";break;
            case '9':return "订单已被创建，但订单因为无货导致关闭";break;
        }
    }
  }
};
</script>

<style scoped>
.order-title {
  text-align: center;
}

.price {
  color: #e4393c;
  font-size: 16px !important;
}
.order-info {
  font-size: 16px;
  line-height: 2em;
}
</style>