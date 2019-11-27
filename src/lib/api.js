import axios from 'axios';

axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.baseURL = "/api"

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

// 获取商家的订单
export const getOrderSeller = params => {
    return axios.get("/order/seller").then(res => res.data)
}

// 获取商家的订单
export const getOrderSellerItem = params => {
    return axios.get("/order/seller/item/" + params).then(res => res.data)
}

// 获取买家的全部订单
export const getOrderBuyer = params => {
    return axios.get("/order/buyer").then(res => res.data)
}

// 修改商品信息
export const editItem = params => {
    return axios.put("/item", params).then(res => res.data)
}

// 确认自己的信息
export const confirm = params => {
    return axios.post("/item/confirm/" + params).then(res => res.data)
}

// 获取商家的商品列表
export const getItemBySeller = params => {
    return axios.get("/item/seller/" + params).then(res => res.data)
}

// 获取买家信息
export const getBuyerInfo = params => {
    return axios.get("/buyer/info").then(res => res.data)
}

// 获取商家信息
export const getSellerInfo = params => {
    return axios.get("/seller/info").then(res => res.data)
}

// 修改商家信息
export const updateSeller = params => {
    return axios.put("/seller", params).then(res => res.data)
}

// 修改买家信息
export const updateBuyer = params => {
    return axios.put("/buyer", params).then(res => res.data)
}