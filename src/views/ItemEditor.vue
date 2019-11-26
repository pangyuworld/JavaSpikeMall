<template>
  <Row type="flex" align="middle" justify="center" class-name="item-editor">
    <Col span="12">
      <ItemEditor :item="updateData()"></ItemEditor>
    </Col>
  </Row>
</template>

<script>
import ItemEditor from "@/components/itemEditor";
export default {
  components: {
    ItemEditor
  },
  data() {
    return {
      itemId: 0,
      itemInfo: {
        itemId: 0,
        itemName: "",
        itemCount: 0,
        itemPrice: 0,
        itemImg: "",
        sellerId: 0,
        itemInfo: ""
      }
    };
  },
  mounted() {
    this.getItemId();
    this.getItem();
  },
  methods: {
    getItemId() {
      this.itemId = this.$route.params.itemId;
    },
    getItem() {
      this.$api.getItemById(this.itemId).then(res => {
        this.itemInfo = res.data;
      });
    },
    updateData(){
        this.$set(this.itemInfo,"itemPrice",parseFloat(this.itemInfo.itemPrice))
        this.$set(this.itemInfo,"itemCount",parseFloat(this.itemInfo.itemCount))
        return this.itemInfo
    }
  }
};
</script>

<style>
.item-editor {
  margin-top: 20px;
}
</style>