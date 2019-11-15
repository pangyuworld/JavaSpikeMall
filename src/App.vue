<template>
  <div id="app">
    <Layout>
      <Header>
        <myHeader />
      </Header>
      <Content>
        <router-view />
      </Content>
      <Footer>Footer</Footer>
    </Layout>
  </div>
</template>
<script>
import myHeader from "@/components/header/index";
export default {
  mounted: function() {
    this.$cookie.set("loginType", "seller", 1);
  },
  components: {
    myHeader
  }
};
</script>
<style>
</style>
