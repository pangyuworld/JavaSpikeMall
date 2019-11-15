import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from "../views/Login.vue"
import Register from '../views/Register'

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
}]

const router = new VueRouter({
    routes
})

export default router