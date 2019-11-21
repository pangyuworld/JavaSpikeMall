import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import axios from 'axios'
import VueAxios from 'vue-axios'
import api from "./lib/index"
import * as cookieTool from './lib/cookieTool'
import * as formatDate from "./lib/date"
Vue.use(VueAxios, axios);

Vue.prototype.$cookie = cookieTool
Vue.prototype.$date = formatDate
Vue.prototype.$api = api
Vue.config.productionTip = false
Vue.use(ViewUI);

new Vue({
    router,
    store,
    render: function(h) {
        return h(App)
    }
}).$mount('#app')