<template>
  <div class="home">
    <div v-for="(item,index) in itemList" :key="index">
      <Row
        type="flex"
        align="middle"
        justify="center"
        class-name="my-item-list"
        :gutter="16"
        v-if="index%3==0"
      >
        <Col :span="4">
          <ItemList :item="itemList[index+0]"></ItemList>
        </Col>
        <Col :span="4">
          <ItemList :item="itemList[index+1]" v-if="itemList[index+1]"></ItemList>
        </Col>
        <Col :span="4">
          <ItemList :item="itemList[index+2]" v-if="itemList[index+2]"></ItemList>
        </Col>
      </Row>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import ItemList from "@/components/itemList";
export default {
  data() {
    return {
      itemList: [
        {
          itemId: 0,
          itemName: "",
          itemCount: 0,
          itemPrice: 0,
          itemImg: " ",
          sellerId: 0,
          itemInfo: ""
        }
      ]
    };
  },
  mounted() {
    this.getItemList();
  },
  methods: {
    getItemList() {
      this.$api.getItemList().then(res => {
        if (res.success === true) {
          this.itemList = res.data;
        } else {
          this.$Message.error(res.response.data.message);
        }
      });
    }
  },
  components: {
    ItemList
  }
};
</script>
<style scoped>
.my-item-list {
  margin-top: 50px;
}
</style>