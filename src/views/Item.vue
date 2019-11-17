<template>
  <div>
    <Row type="flex" align="middle" justify="center" class-name="my-item">
      <Col span="12">
        <Item :item="item" />
      </Col>
    </Row>
  </div>
</template>

<script>
import Item from "@/components/item";
export default {
  data() {
    return {
      item: {
        itemId: 0,
        itemName: "",
        itemCount: 0,
        itemPrice: 0,
        itemImg: " ",
        sellerId: 0,
        itemInfo: ""
      }
    };
  },
  mounted() {
    this.getItem();
  },
  methods: {
    getItem() {
      this.$api.getItemById(this.$route.params.itemId).then(res => {
        if (res.success === true) {
          this.item = res.data;
        }
      });
    }
  },
  components: {
    Item
  }
};
</script>

<style scope>
.my-item {
  margin: 50px;
}
</style>