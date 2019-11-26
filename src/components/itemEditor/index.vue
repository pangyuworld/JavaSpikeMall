<template>
  <Card>
    <div slot="title" class="editor-title">
      <h3>修改商品信息</h3>
    </div>
    <List item-layout="vertical">
      <ListItem>
        <ListItemMeta>
          <div slot="title">
            <h4>{{item.itemName}}</h4>
          </div>
          <div slot="description" class="editor-des">
            <Form ref="item" :model="item" :label-width="80" :rules="rules" style="width:90%">
              <FormItem label="商品名" prop="itemName">
                <Input placeholder="输入商品名" v-model="item.itemName" clearable />
              </FormItem>
              <FormItem label="单价" prop="itemPrice">
                <InputNumber
                  style="width:150px;"
                  :min="0"
                  v-model.number="item.itemPrice"
                  :formatter="value => `￥ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
                  :parser="value => value.replace(/\$\s?|(,*)/g, '')"
                  :precision="2"
                ></InputNumber>
              </FormItem>
              <FormItem label="库存" prop="itemCount">
                <InputNumber style="width:150px;" :min="0" v-model.number="item.itemCount"></InputNumber>
              </FormItem>
              <FormItem label="商品图片" prop="itemImg">
                <Input placeholder="输入商品图片地址" type="url" v-model="item.itemImg" clearable />
              </FormItem>
              <FormItem label="商品描述" prop="itemInfo">
                <Input v-model="item.itemInfo" type="textarea" placeholder="输入商品描述" autosize />
              </FormItem>
              <FormItem>
                  <Button type="success" @click="sumbitEdit()">确认修改</Button>
              </FormItem>
            </Form>
          </div>
        </ListItemMeta>
        <div slot="extra" class="item-img-editor">
          <img :src="item.itemImg" width="100%" height="100%" />
        </div>
      </ListItem>
    </List>
  </Card>
</template>

<script>
export default {
  data() {
    return {
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
  props: {
    item: Object
  },
  methods:{
      sumbitEdit(){
          this.$api.editItem(this.item).then(res=>{
              if(res.data===true){
                  this.$Message.success("修改成功")
              }else{
                  this.$Message.error("修改失败")
              }
          })
      }
  }
};
</script>

<style>
.editor-title {
  text-align: center;
}
.item-img-editor{
    max-width: 381px;
    max-height: 381px;
    height: 381px;
    min-width: 381px;
}
</style>