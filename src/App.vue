<template>
  <div id="app">
    <Layout>
      <Header>
        <myHeader :loginType="loginType" />
      </Header>
      <Content>
        <router-view />
      </Content>
      <Footer class="my-fotter">Footer</Footer>
    </Layout>
  </div>
</template>
<script>
import myHeader from "@/components/header/index";
export default {
  data() {
    return {
      loginType: null
    };
  },
  mounted: function() {
    this.updateStatus();
  },
  components: {
    myHeader
  },
  beforeUpdate() {
    this.updateStatus();
  },
  methods: {
    updateStatus() {
      console.log("561651")
      this.loginType = this.$cookie.get("loginType");
    }
  }
};
</script>
<style>
body {
  background-color: #f5f7f9 !important;
  background-size: 100% 100%;
}
</style>
<style scoped>
.my-fotter {
  position: absolute;
  bottom: 0;
  text-align: center;
  width: 100%;
}
</style>