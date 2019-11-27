<template>
  <Row type="flex" align="middle" justify="center" class-name="self-editor">
    <Col span="6">
      <Card>
        <div slot="title" style="text-align:center">
          <h3>我的个人信息</h3>
        </div>
        <Form :label-width="80">
          <FormItem label="登录名">
            <Input v-model="userInfo.userName" :disabled="true" />
          </FormItem>
          <FormItem label="用户名">
            <Input v-model="userInfo.sellerName" />
          </FormItem>
          <FormItem>
            <Button type="success" @click="submitUpdate">确认修改</Button>
          </FormItem>
        </Form>
      </Card>
    </Col>
  </Row>
</template>

<script>
export default {
  data() {
    return {
      loginType: null,
      userInfo: {
        sellerId: 0,
        sellerName: "",
        userName: ""
      }
    };
  },
  mounted() {
    this.getLoginType();
    this.getInfo();
  },
  methods: {
    getLoginType() {
      this.loginType = this.$route.params.loginType;
    },
    getInfo() {
      if (this.loginType === "seller") {
        this.$api.getSellerInfo().then(res => {
          this.userInfo = res.data;
        });
      } else if (this.loginType === "buyer") {
        this.$api.getBuyerInfo().then(res => {
          this.userInfo = res.data;
        });
      }
    },
    submitUpdate() {
      if (this.loginType === "seller") {
        this.$api.updateSeller(this.userInfo).then(res => {
          if (res.data === true) {
            this.$Message.success("修改成功");
          } else {
            this.$Message.error("修改失败");
          }
        });
      } else if (this.loginType === "buyer") {
        this.$api.updateBuyer(this.userInfo).then(res => {
          if (res.data === true) {
            this.$Message.success("修改成功");
          } else {
            this.$Message.error("修改失败");
          }
        });
      }
    }
  }
};
</script>

<style>
.self-editor {
  margin-top: 20px;
}
</style>