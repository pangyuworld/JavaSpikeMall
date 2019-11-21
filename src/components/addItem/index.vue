<template>
  <Card>
    <div span="title" class="add-item-title">
      <h2>添加商品</h2>
      <Divider />
    </div>
    <Row type="flex" align="middle" justify="center">
      <Col span="12">
        <Form ref="item" :model="item" :label-width="80" :rules="rules">
          <FormItem label="商品名" prop="itemName">
            <Input placeholder="输入商品名" v-model="item.itemName" clearable />
          </FormItem>
          <FormItem label="单价" prop="itemPrice">
            <InputNumber
              :min="0"
              v-model="item.itemPrice"
              :formatter="value => `$ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
              :parser="value => value.replace(/\$\s?|(,*)/g, '')"
            ></InputNumber>
          </FormItem>
          <FormItem label="库存" prop="itemCount">
            <InputNumber :min="0" v-model="item.itemCount"></InputNumber>
          </FormItem>
          <FormItem label="商品图片" prop="itemImg">
            <Input placeholder="输入商品图片地址" type="url" v-model="item.itemImg" clearable />
          </FormItem>
          <div class="item-img">
            <Spin size="large" v-if="item.itemImg==null||item.itemImg==''"></Spin>
            <img width="100%" v-else height="100%" :src="item.itemImg" />
          </div>
          <FormItem label="商品描述" prop="itemInfo">
            <Input v-model="item.itemInfo" type="textarea" placeholder="输入商品描述" autosize />
          </FormItem>
          <FormItem>
            <Button type="primary" @click="addItem">添加</Button>
            <Button @click="clear" style="margin-left: 8px">清空</Button>
          </FormItem>
        </Form>
      </Col>
    </Row>
  </Card>
</template>

<script>
export default {
  data() {
    return {
      item: {
        itemName: "",
        itemCount: 0,
        itemPrice: 0,
        itemImg: "",
        itemInfo: ""
      },
      rules: {
        itemName: [
          {
            required: true,
            message: "请输入商品名"
          },
          {
            pattern: /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_-]|["',，.。/、\]\[【】\\n\s！!?？——_<>%;‘’；)《（）》(&+=`“”·*#@@]){0,}$/,
            message: "请输入合法字符"
          }
        ],
        itemInfo: [
          {
            required: true,
            message: "请输入商品介绍"
          },
          {
            pattern: /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_-]|["',，.。/、\]\[【】\\n\s！!?？——_<>%;‘’；)《（）》(&+=`“”·*#@@]){0,}$/,
            message: "请输入合法字符"
          }
        ],
        itemCount: [
          {
            required: true,
            message: "请输入商品库存"
          }
        ],
        itemPrice: [
          {
            required: true,
            message: "请输入商品单价"
          }
        ],
        itemImg: [
          {
            pattern:
              "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]",
            message: "请输入合法的网址"
          },
          {
            required: true,
            message: "请输入商品图片"
          }
        ]
      }
    };
  },
  methods: {
    addItem() {
      // 这个参数是检验验证是否通过
      let checkResult = false;
      // 开始对参数进行验证
      this.$refs["item"].validate(valid => {
        if (!valid) {
          // 验证没有通过
          this.$Message.error("请按格式输入信息");
          checkResult = false;
        } else {
          // 验证通过
          checkResult = true;
        }
      });
      console.log(checkResult);
      // 如果验证没有通过就直接跳出
      if (!checkResult) {
        return;
      }
      this.$api
        .addItem(this.item)
        .then(res => {
          if (res.success === true) {
            // 登录成功，弹出提示信息
            this.$Message.success("添加商品成功");
            setTimeout(e => {
              this.$router.push({
                path: "/"
              });
            }, 1500);
          }
        })
        .catch(err => {
          this.$Message.error(err.response.data.message);
        });
    },
    clear() {
      this.item = {
        itemName: "",
        itemCount: 0,
        itemPrice: 0,
        itemImg: "",
        sellerId: 0,
        itemInfo: ""
      };
    }
  }
};
</script>

<style scpoed>
.add-item-title {
  text-align: center;
}
.item-img {
  max-width: 200px;
  max-height: 200px;
  margin-left: 120px;
}
</style>