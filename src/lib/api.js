import axios from 'axios';
import qs from "qs"
import * as cookie from "./cookieTool"

axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.baseURL = "/api"
    // axios.interceptors.request.use((config) => {
    //     const token = cookie.get('token')
    //     if (token) {
    //         config.headers.Authorization = token
    //     }
    //     return config
    // });

// 卖家登录
export const sellerLogin = params => {
        return axios.post("/seller/login", params).then(res => res.data)
    }
    // 买家登录
export const buyerLogin = params => {
    return axios.post("/buyer/login", params).then(res => res.data)
}

// 卖家注册
export const sellerRegister = params => {
    return axios.post("/seller/register", params).then(res => res.data)
}

// 买家注册
export const buyerRegister = params => {
    return axios.post("/buyer/register", params).then(res => res.data).catch(err => err)
}

// 添加商品
export const addItem = params => {
    return axios.post("/item", params).then(res => res.data)
}

// 根据id获取商品
export const getItemById = params => {
    return axios.get("/item/" + params).then(res => res.data)
}

// 获取商品列表
export const getItemList = params => {
    return axios.get("/item").then(res => res.data)
}

// 下订单
export const addOrder = params => {
    return axios.post("/order", params).then(res => res.data)
}

// 获取订单状态
export const getOrderStatus = params => {
    return axios({
        url: "/order/status?orderNumber=" + params,
        method: "get"
    }).then(res => res.data)
}