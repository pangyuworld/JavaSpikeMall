import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from "../views/Login.vue"
import Register from '../views/Register'
import Item from '../views/Item'
import Result from '../views/Result.vue'

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
}]

const router = new VueRouter({
    routes
})

export default router