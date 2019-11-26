<template>
  <Row type="flex" align="middle" justify="center" class-name="order-list">
    <Col span="10">
      <OrderList :orderList="orderListData"></OrderList>
    </Col>
  </Row>
</template>

<script>
import OrderList from "@/components/orderList";
export default {
  components: {
    OrderList
  },
  data() {
    return {
      itemId: null,
      orderListData: [
        {
          orderId: 0,
          orderTime: "1970-01-01T00:00:00.000+0000",
          itemId: 0,
          buyerId: 0,
          orderStatus: 0,
          orderNumber: 0,
          orderCount: 0,
          itemId: "4",
          itemImg: "",
          itemInfo: "",
          itemName: "",
          itemPrice: 0,
          buyerName: ""
        }
      ]
    };
  },
  mounted() {
    this.getItemId();
    if (this.itemId == null) {
      this.getOrderBySeller();
    } else {
      this.getOrderBySellerAndItem();
    }
  },
  methods: {
    getItemId() {
      this.itemId = this.$route.params.itemId;
    },
    getOrderBySeller() {
      this.$api.getOrderSeller().then(res => {
        this.orderListData = res.data;
      });
    },
    getOrderBySellerAndItem() {
      this.$api.getOrderSellerItem(this.itemId).then(res => {
        this.orderListData = res.data;
      });
    }
  },
  watch: {
    $route(to, from) {
      this.getItemId();
      if (this.itemId == null) {
        this.getOrderBySeller();
      } else {
        this.getOrderBySellerAndItem();
      }
    }
  }
};
</script>

<style scpoed>
.order-list {
  margin-top: 25px;
}
</style>