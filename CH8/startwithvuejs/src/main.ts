import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import BootstrapVue from 'bootstrap-vue'
import Toasted from 'vue-toasted'
import axios from 'axios'
import VueAxios from 'vue-axios'

const axiosInstance = axios.create({
  baseURL: process.env.VUE_APP_RESTFUL_BASE_URL
})

axiosInstance.interceptors.response.use(response => {
  return response
}, error => {
  Vue.toasted.error('Ops, an unexpected error occurred')
  return Promise.reject(error)
})

Vue.config.productionTip = false
Vue.use(BootstrapVue)
Vue.use(Toasted, { duration: 2000 })
Vue.use(VueAxios, axiosInstance)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
