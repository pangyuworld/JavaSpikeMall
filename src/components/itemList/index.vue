<template>
  <Card :to="'/item/'+item.itemId" class="item-card">
    <div slot="title" class="img">
      <img
        style="width:100%"
        :src="item.itemImg"
      />
    </div>
    <div>
      <p class="price">￥{{item.itemPrice}}</p>
      <h4><strong>{{item.itemName}}</strong></h4>
      <p class="itemInfo">{{item.itemInfo}}</p>
      <span class="itemCount">剩余库存：{{item.itemCount>1000?'1000+':item.itemCount}}</span>
    </div>
  </Card>
</template>

<script>
export default {
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
  }
};
</script>

<style scoped>
.price {
  color: #e4393c;
  font-size: 20px;
}
.itemInfo {
  color: #657180;
}
.itemCount {
  color: #9ea7b4;
}
.img{
  height: 300px;
  max-width: 300px;
}
.item-card{
  height: 478px;
}
</style>