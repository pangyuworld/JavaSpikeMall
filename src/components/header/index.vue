<template>
  <div>
    <!-- logo -->
    <div class="logo"></div>
    <Menu theme="dark" mode="horizontal" active-name="home">
      <!-- 主页按钮 -->
      <MenuItem name="home" to="/">
        <Icon type="ios-home-outline" />主页
      </MenuItem>
      <!-- 买家 -->
      <Submenu name="buyer">
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
      <Submenu name="seller">
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
    </Menu>
  </div>
</template>

<script>
export default {};
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