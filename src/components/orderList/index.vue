<template>
  <Card>
    <div slot="title" class="order-list-title">
      <h3>查看订单信息</h3>
    </div>
    <List item-layout="vertical">
      <ListItem v-for="order in orderList" :key="order.orderId">
        <ListItemMeta>
          <div slot="title">
            <p>订单号：{{order.orderNumber}}</p>
          </div>
          <div slot="description" class="order-des">
            <p>下单时间：{{formatDate(order.orderTime)}}</p>
            <p>下单人：{{order.buyerName}}</p>
            <p>下单商品：{{order.itemName}}</p>
            <p>
              商品单价：
              <span class="order-price">￥{{order.itemPrice}}</span>
            </p>
            <p>购买数量：{{order.orderCount}}</p>
            <p>
              总价：
              <span class="order-price">￥{{order.orderCount*order.itemPrice}}</span>
            </p>
          </div>
        </ListItemMeta>
        <div slot="extra" class="item-img">
          <img :src="order.itemImg" width="100%" height="100%" />
        </div>
      </ListItem>
    </List>
  </Card>
</template>

<script>
export default {
  props: {
    orderList: Array
  },
  methods:{
    formatDate(date){
        return this.$date.formatDate(new Date(date))
    },
  }
};
</script>

<style scoped>
.order-list-title {
  text-align: center;
}
.order-des {
  margin-left: 20px;
  color: #657180;
  line-height: 180%;
}
.order-price {
  color: #e4393c;
  font-size: 20px;
}
.item-img{
  max-width: 220px;
  max-height: 220px;
}
</style>