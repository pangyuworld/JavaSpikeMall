import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from "../views/Login.vue"
import Register from '../views/Register'
import Item from '../views/Item'
import Result from '../views/Result.vue'
import AddItem from "../views/AddItem.vue"
import SellerOrder from "../views/SellerOrder.vue"
import BuyerOrder from "../views/BuyerOrder.vue"
import ItemEditor from "../views/ItemEditor.vue"
import SellerItem from "../views/ItemSeller.vue"

Vue.use(VueRouter)

const routes = [{
    path: '/',
    name: 'home',
    component: Home
}, {
    path: "/seller/login",
    name: "sellerLogin",
    component: Login
}, {
    path: "/seller/register",
    name: "sellerRegister",
    component: Register
}, {
    path: "/buyer/login",
    name: "buyerLogin",
    component: Login
}, {
    path: "/buyer/register",
    name: "buyerRegister",
    component: Register
}, {
    path: "/item/:itemId",
    name: "item",
    component: Item
}, {
    path: "/result/:orderNumber",
    name: "result",
    component: Result
}, {
    path: "/add/item",
    name: "addItem",
    component: AddItem
}, {
    path: "/seller/order/:itemId",
    name: "order",
    component: SellerOrder
}, {
    path: "/seller/order",
    name: "sellerOrder",
    component: SellerOrder
}, {
    path: "/buyer/order",
    name: "buyerOrder",
    component: BuyerOrder
}, {
    path: "/seller/item/:itemId",
    name: "itemEditor",
    component: ItemEditor
}, {
    path: "/item/seller/:sellerId",
    name: "sellerItem",
    component: SellerItem
}]

const router = new VueRouter({
    routes
})

export default router