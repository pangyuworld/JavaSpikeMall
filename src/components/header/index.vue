<template>
  <div>
    <!-- logo -->
    <div class="logo"></div>
    <Menu theme="dark" mode="horizontal" active-name="home" @on-select="select">
      <!-- 主页按钮 -->
      <MenuItem name="home" to="/">
        <Icon type="ios-home-outline" />主页
      </MenuItem>
      <!-- 买家 -->
      <Submenu name="buyer" v-if="!loginType">
        <span slot="title">
          <Icon type="md-paw" />
          <span>我是客户</span>
        </span>
        <!-- 买家登录界面 -->
        <MenuItem name="buyerLogin" to="/buyer/login">
          客户登录
          <Icon type="md-log-in" />
        </MenuItem>
        <!-- 买家注册界面 -->
        <MenuItem name="buyerRegister" to="/buyer/register">
          客户注册
          <Icon type="md-person-add" />
        </MenuItem>
      </Submenu>
      <!-- 卖家 -->
      <Submenu name="seller" v-if="!loginType">
        <span slot="title">
          <Icon type="ios-calculator" />
          <span>我是商家</span>
        </span>
        <!-- 买家登录界面 -->
        <MenuItem name="sellerLogin" to="/seller/login">
          商家登录
          <Icon type="md-log-in" />
        </MenuItem>
        <!-- 买家注册界面 -->
        <MenuItem name="sellerRegister" to="/seller/register">
          商家注册
          <Icon type="md-person-add" />
        </MenuItem>
      </Submenu>
      <MenuItem name="userName" v-if="loginType" :to="'/info/'+loginType">
        <Icon type="md-paw" v-if="loginType=='buyer'" />
        <Icon type="ios-calculator" v-if="loginType=='seller'" />
        {{userName}}
      </MenuItem>
      <MenuItem name="addItem" v-if="loginType=='seller'" to="/add/item">
        <Icon type="md-add" />添加商品
      </MenuItem>
      <MenuItem name="addItem" v-if="loginType=='seller'" to="/seller/order">
        <Icon type="md-add" />查看订单
      </MenuItem>
      <MenuItem name="addItem" v-if="loginType=='buyer'" to="/buyer/order">
        <Icon type="md-add" />查看订单
      </MenuItem>
      <MenuItem name="signOut" @click="signOut" v-if="loginType">
        <Icon type="md-log-out" />注销
      </MenuItem>
    </Menu>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userName: null
    };
  },
  props: {
    loginType: null
  },
  mounted() {},
  methods: {
    signOut() {
      this.$cookie.del("loginType");
      this.$cookie.del("token");
      this.$parent.$parent.$parent.updateStatus();
      this.$Message.success("注销成功");
    },
    select(e) {
      switch (e) {
        case "signOut":
          this.signOut();
          break;
      }
    }
  },
  watch: {
    loginType() {
      this.userName = decodeURI(this.$cookie.get("userName"));
    }
  }
};
</script>

<style>
.logo {
  /* background-image:; */
  background-color: #fff;
  width: 120px;
  height: 31px;
  margin: 16px 28px 16px 0;
  float: left;
}
</style>