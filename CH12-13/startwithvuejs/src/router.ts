import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import FoodProduct from './views/FoodProduct.vue'
import FoodProductList from './components/foodProduct/FoodProductList.vue'
import FoodProductNew from './components/foodProduct/FoodProductNew.vue'
import FoodProductUpdate from './components/foodProduct/FoodProductUpdate.vue'
import FoodService from './views/FoodService.vue'
import FoodServiceView from './components/foodService/FoodServiceView.vue'
import FoodServiceNew from './components/foodService/FoodServiceNew.vue'
import FoodServiceUpdate from './components/foodService/FoodServiceUpdate.vue'
import Login from './views/Login.vue'
import Delivery from './views/Delivery.vue'
import DeliveryEmail from './components/delivery/DeliveryEmail.vue'
import FoodServiceList from './components/delivery/FoodServiceList.vue'
import FoodProductsByService from './components/delivery/FoodProductsByService.vue'
import CartItems from './components/delivery/CartItems.vue'
import DeliverySummary from './components/delivery/DeliverySummary.vue'
import AuthorizationCallback from './components/security/AuthorizationCallback.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/auth',
      name: 'auth',
      component: AuthorizationCallback
    },
    {
      path: '/food_product',
      component: FoodProduct,
      children: [
        {
          path: '/',
          name: 'food_product_list',
          component: FoodProductList
        },
        {
          path: 'new/:foodService',
          name: 'food_product_new',
          props: (route) => { return { foodService: route.params.foodService } },
          component: FoodProductNew
        },
        {
          path: ':id',
          name: 'food_product_update',
          props: (route) => { return { id: route.params.id } },
          component: FoodProductUpdate
        }
      ]
    },
    {
      path: '/food_service',
      component: FoodService,
      children: [
        {
          path: 'update/:id',
          name: 'food_service_update',
          props: (route) => { return { id: route.params.id } },
          component: FoodServiceUpdate
        },
        {
          path: 'new',
          name: 'food_service_new',
          component: FoodServiceNew
        },
        {
          path: ':id',
          name: 'food_service_view',
          props: (route) => { return { id: route.params.id } },
          component: FoodServiceView
        }
      ]
    },
    {
      path: '/delivery',
      component: Delivery,
      children: [
        {
          path: '',
          name: 'delivery_email',
          component: DeliveryEmail
        },
        {
          path: 'food_service',
          name: 'food_service_list',
          component: FoodServiceList
        },
        {
          path: 'food_service/:foodService',
          name: 'food_products_by_service',
          props: (route) => { return { foodService: route.params.foodService } },
          component: FoodProductsByService
        },
        {
          path: 'cart/:foodService',
          name: 'cart',
          props: (route) => { return { foodService: route.params.foodService } },
          component: CartItems
        },
        {
          path: 'summary/:email',
          name: 'delivery_summary',
          props: (route) => { return { email: route.params.email } },
          component: DeliverySummary
        }
      ]
    },
    {
      path: '/about',
      name: 'about',
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    }
  ]
})
