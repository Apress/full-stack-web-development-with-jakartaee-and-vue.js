import Vue from 'vue'
import Vuex from 'vuex'
import { FoodProduct } from './entities/FoodProduct'
import { FoodService } from './entities/FoodService'
import { Item } from './entities/Item'
import { State } from './entities/State'
import { Cart } from './entities/Cart'
import { Delivery } from './entities/Delivery'

Vue.use(Vuex)

const stateBase: State = {
  foodProducts: [
    FoodProduct.newFoodProduct(1, 'Napolitana', 'Napolitana and apple', 16, 'http://localhost:8080/images/product/1.jpeg', 'email1@email.com'),
    FoodProduct.newFoodProduct(2, 'Meat', 'Meat small', 12, 'http://localhost:8080/images/product/2.jpeg', 'email1@email.com'),
    FoodProduct.newFoodProduct(3, 'Cheese', 'Cheese huge', 14, 'http://localhost:8080/images/product/3.jpeg', 'email1@email.com'),
    FoodProduct.newFoodProduct(4, 'Chicken', 'Chicken small', 11, 'http://localhost:8080/images/product/4.jpeg', 'email1@email.com'),
    FoodProduct.newFoodProduct(5, 'Chicken huge', 'Chicken huge', 14, 'http://localhost:8080/images/product/5.jpeg', 'email1@email.com'),
    FoodProduct.newFoodProduct(6, 'Vegan', 'Vegan huge', 15, 'http://localhost:8080/images/product/6.jpeg', 'email1@email.com'),
    FoodProduct.newFoodProduct(7, 'Hawaiana', 'Hawaiana huge', 14, 'http://localhost:8080/images/product/7.jpeg', 'email1@email.com'),
    FoodProduct.newFoodProduct(8, 'From House', 'From House', 10, 'http://localhost:8080/images/product/8.jpeg', 'email1@email.com'),
    FoodProduct.newFoodProduct(9, 'Vegetarian', 'Vegetarian', 14, 'http://localhost:8080/images/product/9.jpeg', 'email1@email.com'),
    FoodProduct.newFoodProduct(10, 'Rice', 'Rice double', 11, 'http://localhost:8080/images/product/10.jpeg', 'email2@email.com')
  ],
  foodServices: [
    FoodService.newFoodService('email1@email.com', 'Service1', 'Street1', 'PIZZA', 1, 'http://localhost:8080/images/services/1.jpg', 'pass'),
    FoodService.newFoodService('email2@email.com', 'Service2', 'Street2', 'PIZZA', 2, 'http://localhost:8080/images/services/2.jpg', 'pass'),
    FoodService.newFoodService('email3@email.com', 'Service3', 'Street3', 'CHINESE', 2, 'http://localhost:8080/images/services/3.jpg', 'pass'),
    FoodService.newFoodService('email4@email.com', 'Service4', 'Street5', 'CHINESE', 3, 'http://localhost:8080/images/services/4.jpg', 'pass'),
    FoodService.newFoodService('email5@email.com', 'Service5', 'Street6', 'CHICKEN', 5, 'http://localhost:8080/images/services/5.jpg', 'pass'),
    FoodService.newFoodService('email6@email.com', 'Service6', 'Street7', 'CHICKEN', 3, 'http://localhost:8080/images/services/6.jpg', 'pass'),
    FoodService.newFoodService('email7@email.com', 'Service7', 'Street8', 'PASTA', 1, 'http://localhost:8080/images/services/7.jpg', 'pass'),
    FoodService.newFoodService('email8@email.com', 'Service8', 'Street9', 'PASTA', 3, 'http://localhost:8080/images/services/8.jpg', 'pass')
  ],
  cart: Cart.emptyCart(),
  deliveries: [],
  currentFoodServiceLoggedIn: '',
  currentDeliveryEmail: ''
}

export default new Vuex.Store<State>({
  state: stateBase,
  mutations: {
    saveFoodProduct (state:State, foodProduct:FoodProduct) {
      foodProduct.id = state.foodProducts.length + 1
      state.foodProducts.push(foodProduct)
    },
    removeFoodProduct (state:State, foodProductToRemove:FoodProduct) {
      const index = state.foodProducts.findIndex(foodProduct => foodProduct.id === foodProductToRemove.id)

      Vue.delete(state.foodProducts, index)
    },
    updateFoodProduct (state:State, foodProductToUpdate:FoodProduct) {
      let foodProduct:FoodProduct | undefined = state.foodProducts.find(foodProduct => foodProduct.id === foodProductToUpdate.id)

      foodProduct!.copyFoodProduct(foodProductToUpdate)
    },
    saveFoodService (state:State, foodService:FoodService) {
      state.foodServices.push(foodService)
    },
    removeFoodService (state:State, foodServiceToRemove:FoodService) {
      const index = state.foodServices.findIndex(foodService => foodService.email === foodServiceToRemove.email)

      Vue.delete(state.foodServices, index)
    },
    updateFoodService (state:State, foodServiceToUpdate:FoodService) {
      let foodService:FoodService | undefined = state.foodServices.find(foodService => foodService.email === foodServiceToUpdate.email)

      foodService!.copyFoodService(foodServiceToUpdate)
    },
    setCurrentFoodServiceLoggedIn (state:State, foodService:string) {
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
    },
    addDelivery (state:State, delivery:Delivery) {
      state.deliveries.push(delivery)
    }
  },
  actions: {

  },
  getters: {
    getFoodProductById: (state) => (id:number) => {
      return state.foodProducts.find(foodProduct => foodProduct.id === id)
    },
    getFoodProductByFoodService: (state) => (foodService:string, page:number, pageSize:number) => {
      return state.foodProducts.filter(foodProduct => foodProduct.foodService === foodService).slice((page - 1) * pageSize, (page - 1) * pageSize + pageSize)
    },
    getFoodServiceByEmail: (state) => (email:string) => {
      return state.foodServices.find(foodService => foodService.email === email)
    },
    getFoodServiceByFoodType: (state) => (foodType:string, page:number, pageSize:number) => {
      return state.foodServices.filter(foodService => foodType === 'ALL' || foodService.foodType === foodType).slice((page - 1) * pageSize, (page - 1) * pageSize + pageSize)
    },
    getFoodServiceByEmailAndPassword: (state) => (email:string, password:string) => {
      return state.foodServices.find(foodService => foodService.email === email && foodService.password === password)
    },
    getCurrentFoodServiceLoggedIn: (state) => () => {
      return state.currentFoodServiceLoggedIn
    },
    getCurrentDeliveryEmail: (state) => () => {
      return state.currentDeliveryEmail
    },
    getDeliveryByEmail: (state) => (email:string) => {
      return state.deliveries.find(delivery => delivery.email === email)
    }
  }
})
