<template>
  <Row type="flex" align="middle" justify="center" class-name="my-form">
    <Col span="6">
      <Login :pathType="pathType" />
    </Col>
  </Row>
</template>

<script>
import Login from "@/components/form/Login";
export default {
  data() {
    return {
      pathType: ""
    };
  },
  components: {
    Login
  },
  methods: {
    setPathType(path) {
      // 将路径拆分
      var pathTypeArray = path.toString().split("/");
      // 获取路径的属性
      var pathType = pathTypeArray[1];
      if (pathType == "seller" || pathType == "buyer") this.pathType = pathType;
    }
  },
  mounted() {
    var path = this.$route.path;
    this.setPathType(path);
  },
  watch: {
    $route(to, from) {
      this.setPathType(to.path);
    }
  }
};
</script>

<style scope>
.my-form {
  margin-top: 150px;
}
</style>