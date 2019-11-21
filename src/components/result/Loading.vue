<template>
  <div class="spin-container">
    <Spin fix size="large" class="spin">
      <Icon type="ios-loading" size="24" class="demo-spin-icon-load"></Icon>
      <br/>
      <strong>处理中...</strong>
    </Spin>
  </div>
</template>

<script>
export default {};
</script>

<style>
.spin-container {
  position: absolute;
  background-color: #f5f7f9 !important;
  width: 100%;
  font-size: 24px;
}
.demo-spin-icon-load {
  animation: ani-demo-spin 1s linear infinite;
}
@keyframes ani-demo-spin {
  from {
    transform: rotate(0deg);
  }
  50% {
    transform: rotate(180deg);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>