<template>
<div>
  <h2 class="text-center">Cart</h2>
    <div class="row">
      <div class="col-sm">
        <table class="table  table-dark">
          <thead>
            <tr>
              <th>Product</th>
              <th>Amount</th>
              <th>Cost</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in items" v-bind:key="item.foodProduct.id">
              <td>{{item.foodProduct.name}}</td>
              <td>{{item.amount}}</td>
              <td>{{item.amount * item.foodProduct.price}}</td>
              <td><a v-on:click="remove(item)" href="#">Delete</a></td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <td></td>
              <td></td>
              <td>{{total}}</td>
              <td></td>
            </tr>
          </tfoot>
        </table>
    </div>
    </div>
  <div class="row">
    <div class="col-sm">
        <label>Food Service: {{foodServiceData.name}}</label>
    </div>
    <div class="col-sm">
        <label>Delivery Fee: {{foodServiceData.deliveryFee}}</label>
    </div>
  </div>
  <div class="row">
    <div class="col-sm">
        <label>Delivery Email: {{deliveryEmail}}</label>
    </div>
    <div class="col-sm">
        <label>Total + Fee: {{foodServiceData.deliveryFee + total}}</label>
    </div>
  </div>
  <div class="row" v-if="errorMessage">
    <div class="col-sm">
      <div class="alert alert-danger" role="alert">
        {{errorMessage}}
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-sm">
        <div class="form-group">
          <label for="address">Address</label>
          <input
            v-model="address"
            type="text"
            class="form-control"
            id="address"
            placeholder="Address"
            required
          >
        </div>
      </div>
      <div class="col-sm">
        <div class="form-group">
          <label for="phone">Phone</label>
          <input
            v-model="phone"
            type="text"
            class="form-control"
            id="phone"
            placeholder="Phone"
            required
          >
        </div>
      </div>
    </div>
  <div class="row">
    <div class="col-sm">
      <router-link :to="{ name: 'food_products_by_service', params: {foodService: foodService} }" class="btn btn-primary">Add More Products</router-link>
      |
      <a class="btn btn-primary" v-on:click="requestDelivery">Request the Delivery</a>
    </div>
  </div>
</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { Item } from '../../entities/Item'
import { FoodService } from '../../entities/FoodService'
import { Delivery } from '../../entities/Delivery'

@Component
export default class CartItems extends Vue {
  @Prop() private readonly foodService!: string
  private items:Item[] = []
  private deliveryEmail: string = ''
  private foodServiceData: FoodService = FoodService.emptyFoodService()
  private address: string = ''
  private phone: string = ''
  private errorMessage:string = ''

  // lifecycle hook
  mounted () {
    this.getCart()
  }

  getCart () {
    this.items = this.$store.state.cart.items
    this.deliveryEmail = this.$store.getters.getCurrentDeliveryEmail()
    this.foodServiceData = this.$store.getters.getFoodServiceByEmail(
      this.foodService
    )
  }

  remove (itemToRemove:Item) {
    this.$store.commit('removeItemFromCart', itemToRemove)
  }

  requestDelivery () {
    if (this.address === '') {
      this.errorMessage = 'Address is required'

      return false
    }

    if (this.phone === '') {
      this.errorMessage = 'Phone is required'

      return false
    }

    this.$store.commit('addDelivery', Delivery.newDelivery(this.$store.state.cart, this.address, this.deliveryEmail, this.phone, 'PENDING', this.total + this.foodServiceData.deliveryFee))
    this.$router.push({ name: 'delivery_summary', params: { email: this.deliveryEmail } })
  }

  get total () {
    return this.items.length !== 0 ? this.items.map((item: Item) => item.amount * item.foodProduct.price).reduce((previous: number, current: number) => current + previous) : 0
  }
}
</script>
