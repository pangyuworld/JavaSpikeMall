<template></template>

<script>
export default {
  data() {
    return {
      loop: null,
      orderNumber: null
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
        // TODO 这里要完成，加载的时候一个界面，加载完毕后一个界面
      this.$api.getOrderStatus(this.orderNumber).then(res => {
        console.log(res);
        if (res.success === true) {
          console.log(res.data.orderStatus);
          if (res.data.orderStatus !== "0" && res.data.orderStatus !== "1") {
            window.clearInterval(this.loop);
          }
        }
      });
    }
  }
};
</script>

<style>
</style>