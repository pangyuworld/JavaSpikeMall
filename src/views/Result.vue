<template>
  <Row type="flex" align="middle" justify="center" class-name="my-item">
    <Col span="12">
      <Loading v-if="!shut" />
      <Result v-else :order="order" />
    </Col>
  </Row>
</template>

<script>
import Loading from "@/components/result/Loading";
import Result from "@/components/result/Result";
export default {
  components: {
    Loading,
    Result
  },
  data() {
    return {
      loop: null,
      orderNumber: null,
      shut: false,
      order: {
        orderId: 0,
        orderTime: "1970-01-00T00:00:00.000+0000",
        itemId: 0,
        buyerId: 0,
        orderStatus: 0,
        orderNumber: 0,
        orderCount: 0
      }
    };
  },
  mounted() {
    this.orderNumber = this.$route.params.orderNumber;
    this.loop = setInterval(() => {
      this.updateStatus();
    }, 500);
    this.updateStatus();
  },
  methods: {
    updateStatus() {
      this.$api
        .getOrderStatus(this.orderNumber)
        .then(res => {
          console.log(res);
          if (res.success === true) {
            if (res.data.orderStatus !== "0" && res.data.orderStatus !== "1") {
              this.shut = true;
              this.order = res.data;
              window.clearInterval(this.loop);
            }
          }
        })
        .catch(err => {
          this.$Message.error(err.response.data.message);
          window.clearInterval(this.loop);
          this.$router.push({
            path: "/buyer/login"
          });
        });
    }
  }
};
</script>

<style>
</style>