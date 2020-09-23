import Vue from 'vue'
import Vuex from 'vuex'
import { FoodService } from './entities/FoodService'
import { Item } from './entities/Item'
import { State } from './entities/State'
import { Cart } from './entities/Cart'

Vue.use(Vuex)

const stateBase: State = {
  cart: Cart.emptyCart(),
  currentFoodServiceLoggedIn: FoodService.emptyFoodService(),
  currentDeliveryEmail: ''
}

export default new Vuex.Store<State>({
  state: stateBase,
  mutations: {
    setCurrentFoodServiceLoggedIn (state:State, foodService:FoodService) {
      state.currentFoodServiceLoggedIn = foodService
    },
    setCurrentDeliveryEmail (state:State, currentDeliveryEmail:string) {
      state.currentDeliveryEmail = currentDeliveryEmail
    },
    saveItemToCart (state:State, item:Item) {
      state.cart.items.push(item)
    },
    removeItemFromCart (state:State, itemToRemove:Item) {
      const index = state.cart.items.findIndex(item => item.foodProduct.id === itemToRemove.foodProduct.id)

      Vue.delete(state.cart.items, index)
    }
  },
  actions: {

  },
  getters: {
    getCurrentFoodServiceLoggedIn: (state) => () => {
      return state.currentFoodServiceLoggedIn
    },
    getCurrentDeliveryEmail: (state) => () => {
      return state.currentDeliveryEmail
    }
  }
})
