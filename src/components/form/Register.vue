<template>
  <Form ref="userInfo" :model="userInfo" :rules="rules" :label-width="80">
    <FormItem label="用户名" prop="userName">
      <Input placeholder="输入你的姓名" v-model="userInfo.userName"/>
    </FormItem>
    <FormItem label="密码" prop="password">
      <Input v-model="userInfo.password" placeholder="输入你的密码"/>
    </FormItem>
    <FormItem label="确认密码" prop="rePassword">
      <Input v-model="userInfo.rePassword" placeholder="确认你的密码"/>
    </FormItem>
    <FormItem label="真实姓名" prop="realName">
      <Input v-model="userInfo.buyerName" placeholder="输入你的真实姓名" v-if="pathType=='buyer'"/>
      <Input v-model="userInfo.sellerName" placeholder="输入你的真实姓名" v-if="pathType=='seller'"/>
    </FormItem>
  </Form>
</template>

<script>
export default {
  data() {
    const validatePassCheck = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please enter your password again'));
                } else if (value !== this.formCustom.passwd) {
                    callback(new Error('The two input passwords do not match!'));
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
        buyerName: null
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
            message: "请输入的密码",
          },
          {
            pattern: "^\\w{6,20}$",
            message: "密码不符合规则（6-20位字母、数字、下划线）"
          }
        ],
        rePassword:[
          {
            validator: validatePassCheck, trigger: 'blur'
          }
        ],
        realName: [{ required: true, message: "请输入你的真实姓名" }]
      }
    };
  },
  props: {
    pathType: String
  }
};
</script>

<style>
</style>