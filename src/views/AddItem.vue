<template>
  <div>
    <Row type="flex" align="middle" justify="center" class-name="my-item">
      <Col span="12">
        <AddItem/>
      </Col>
    </Row>
  </div>
</template>

<script>
import AddItem from "@/components/addItem"
export default {
    components:{
        AddItem
    }
}
</script>

<style>

</style>