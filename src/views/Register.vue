<template>
  <div>{{pathType}}
      <Register :pathType="pathType"/>
  </div>
</template>

<script>
import Register from "@/components/form/Register";
export default {
  data() {
    return {
      pathType: ""
    };
  },
  components: {
    Register
  },
  methods: {
    setPathType(path) {
      // 将路径拆分
      var pathTypeArray = path.toString().split("/");
      // 获取路径的属性
      var pathType = pathTypeArray[1];
      if (pathType == "seller" || pathType == "buyer") this.pathType = pathType;
      console.log(pathType)
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

<style>
</style>