<template>
  <Card>
    <Row type="flex" justify="space-between">
      <Col span="12">
        <img width="100%" :src="item.itemImg" />
      </Col>
      <Col span="10">
        <h3>
          {{item.itemName}} --
          <router-link :to="'/item/seller/'+item.sellerId">{{item.sellerName}}</router-link>
        </h3>
        <br />
        <p class="itemInfo">{{item.itemInfo}}</p>
        <br />
        <p class="price">￥{{item.itemPrice}}</p>
        <br />
        <p
          class="itemCount"
          v-if="item.itemCount>0"
        >剩余库存：{{item.itemCount>1000?'1000+':item.itemCount}}</p>
        <strong v-else class="itemErr">
          <Icon type="ios-alert" />所选地区无货
          <br />
        </strong>
        <br />购买数量：
        <InputNumber
          v-model="shopping.orderCount"
          :disabled="!item.itemCount>0"
          :max="parseInt(item.itemCount)"
          :min="1"
        ></InputNumber>
        <br />
        <br />
        <div v-if="userType!='seller'" class="shop-btn">
          <Button type="error" v-if="item.itemCount>0" @click="doShop" :disabled="isShop">立即购买</Button>
          <Button type="error" v-else ghost>到货通知</Button>
        </div>
        <div v-else class="shop-btn">
          <Button type="primary" :to="'/seller/order/'+item.itemId">查看售出详情</Button>
          <Button type="info" :to="'/seller/item/'+item.itemId" :disabled="!isMine">修改商品信息</Button>
        </div>
      </Col>
      <Col span="1" />
    </Row>
  </Card>
</template>

<script>
export default {
  data() {
    return {
      isMine: false,
      shopping: {
        orderCount: 1,
        itemId: 0
      },
      isShop: false,
      userType: "seller"
    };
  },
  props: {
    item: {
      itemId: 0,
      itemName: "",
      itemCount: 0,
      itemPrice: 0,
      itemImg: "",
      sellerId: 0,
      itemInfo: ""
    }
  },
  mounted() {
    this.getUserType();
  },
  methods: {
    doShop() {
      this.isShop = true;
      if (!this.$cookie.get("loginType")) {
        alert("请先进行登陆");
        this.isShop = false;
        this.$router.push({
          path: "/buyer/login"
        });
        return;
      }
      this.shopping.itemId = this.item.itemId;
      this.$api.addOrder(this.shopping).then(res => {
        if (res.success === true) {
          this.$router.push({
            path: "/result/" + res.data.orderNumber
          });
        }
      });
      this.isShop = false;
    },
    getUserType() {
      this.userType = this.$cookie.get("loginType");
    },
    confirm() {
      this.$api.confirm(this.item.sellerId).then(res => {
        this.isMine = res.data;
      });
    }
  },
  watch: {
    item() {
      if (this.userType == "seller") {
        this.confirm();
      }
    }
  }
};
</script>

<style scpoed>
.itemInfo {
  color: #657180;
}
.price {
  color: #e4393c;
  font-size: 20px;
}
.itemErr {
  color: #e23a3a;
}
.shop-btn {
  margin-left: 5%;
}
</style>