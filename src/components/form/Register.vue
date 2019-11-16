<template>
  <Card :padding="40">
    <h3 slot="title" style="text-align:center" v-show="pathType=='buyer'">欢迎注册为VIP</h3>
    <h3 slot="title" style="text-align:center" v-if="pathType=='seller'">欢迎注册为商家</h3>
    <Form ref="userInfo" :model="userInfo" :rules="rules" :label-width="80">
      <FormItem label="用户名" prop="userName">
        <Input placeholder="输入你的姓名" v-model="userInfo.userName" />
      </FormItem>
      <FormItem label="密码" prop="password">
        <Input v-model="userInfo.password" placeholder="输入你的密码" type="password" password />
      </FormItem>
      <FormItem label="确认密码" prop="rePassword">
        <Input v-model="userInfo.rePassword" placeholder="确认你的密码" type="password" password />
      </FormItem>
      <FormItem label="真实姓名" prop="realName">
        <Input v-model="userInfo.realName" placeholder="输入你的真实姓名" />
      </FormItem>
      <FormItem>
        <Button type="primary" @click="register">注册</Button>
        <Button @click="toLogin" style="margin-left: 8px">已有账号，登录</Button>
      </FormItem>
    </Form>
  </Card>
</template>

<script>
export default {
  data() {
    /** 两次密码的相等校验 */
    const validatePassCheck = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入你的密码"));
      } else if (value !== this.userInfo.password) {
        callback(new Error("两次输入的密码不一致"));
      } else {
        callback();
      }
    };
    return {
      userInfo: {
        userName: null,
        password: null,
        rePassword: null,
        sellerName: null,
        buyerName: null,
        realName: null
      },
      rules: {
        userName: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur"
          },
          {
            pattern: "^\\w{6,20}$",
            message: "用户名不符合规则（6-20位字母、数字、下划线）"
          }
        ],
        password: [
          {
            required: true,
            message: "请输入的密码"
          },
          {
            pattern: "^\\w{6,20}$",
            message: "密码不符合规则（6-20位字母、数字、下划线）"
          }
        ],
        rePassword: [
          {
            required: true,
            validator: validatePassCheck,
            trigger: "blur",
            message: "两次输入密码不一致"
          }
        ],
        realName: [{ required: true, message: "请输入你的真实姓名" }]
      }
    };
  },
  props: {
    /** 父界面传过来的路径类型 */
    pathType: String
  },
  methods: {
    /** 
     * 注册
     */
    register() {
      // 这个参数是检验验证是否通过
      let checkResult=false;
      // 开始对参数进行验证
      this.$refs["userInfo"].validate(valid => {
        if (!valid) {
          // 验证没有通过
          this.$Message.error("请按格式输入信息");
          checkResult=false;
        }else{
          // 验证通过
          checkResult=true;
        }
      });
      // 如果验证没有通过就直接跳出
      if(!checkResult){
        return
      }
      // 验证通过以后要发送注册请求
      if (this.pathType == "buyer") {
        // 如果是商家注册
        this.userInfo.buyerName = this.userInfo.realName;
        // 下面是主要注册代码
        this.$api.buyerRegister(this.userInfo).then(res => {
          if (res.success === true) {
            // 注册成功，弹出提示框
            this.$Message.success("注册成功");
            // 1.5S以后跳转到登录界面
            setTimeout(e => {
              this.$router.push({
                path: "/" + this.pathType + "/login"
              });
            }, 1500);
          } else {
            // 注册没有成功，弹出提示信息
            this.$Message.error(res.response.data.message);
          }
        });
      } else if (this.pathType == "seller") {
        this.userInfo.sellerName = this.userInfo.realName;
        this.$api.sellerRegister(this.userInfo).then(res => {
          if (res.success === true) {
            this.$Message.success("注册成功");
            setTimeout(e => {
              this.$router.push({
                path: "/" + this.pathType + "/login"
              });
            }, 1500);
          } else {
            this.$Message.error(res.response.data.message);
          }
        });
      }
    },
    /** 
     * 跳转到登录
     */
    toLogin() {
      this.$router.push({
        path: "/" + this.pathType + "/login"
      });
    }
  },
  watch: {
    // 监听pathType的变化，有变化就清空输入框
    pathType() {
      this.userInfo = {
        userName: null,
        password: null,
        rePassword: null,
        sellerName: null,
        buyerName: null,
        realName: null
      };
    }
  }
};
</script>

<style>
</style>