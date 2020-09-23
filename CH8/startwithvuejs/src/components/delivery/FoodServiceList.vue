<template>
  <div>
    <h2 class="text-center">Food Services</h2>

    <div class="row">
      <div class="col-sm">
        <div class="form-group">
          <label for="foodType">Type of Food</label>
          <select class="custom-select" id="foodType" v-model="foodType" v-on:change="resetFilter">
            <option selected value="ALL">ALL</option>
            <option value="PIZZA">PIZZA</option>
            <option value="CHINESE">CHINESE</option>
            <option value="CHICKEN">CHICKEN</option>
            <option value="PASTA">PASTA</option>
          </select>
        </div>
      </div>
    </div>
    <div class="row mt-2" v-if="foodServices.length>0">
      <div class="col-sm">
        <table class="table  table-dark">
          <thead>
            <tr>
              <th></th>
              <th>Name</th>
              <th>Address</th>
              <th>Food Type</th>
              <th>Delivery Fee</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="foodService in foodServices" v-bind:key="foodService.email">
              <td><img class="image img-fluid img-thumbnail" :src="foodService.imageUrl"/></td>
              <td><router-link  :to="{ name: 'food_products_by_service', params: { foodService: foodService.email }}">{{foodService.name}}</router-link></td>
              <td>{{foodService.address}}</td>
              <td>{{foodService.foodType}}</td>
              <td>{{foodService.deliveryFee}}</td>
            </tr>
          </tbody>
        </table>
    </div>
    </div>
    <infinite-loading :identifier="infiniteId"  @infinite="populateFoodProducts"></infinite-loading>
  </div>
</template>

<script lang="ts">
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import InfiniteLoading from 'vue-infinite-loading'
import { Component, Vue, Prop } from 'vue-property-decorator'
import { FoodService } from '../../entities/FoodService'
import { FoodServiceService } from '../../services/FoodServiceService'

@Component({
  components: {
    InfiniteLoading
  }
})
export default class FoodServiceList extends Vue {
  private foodServices:FoodService[] = []
  private foodType:string = 'ALL'
  private infiniteId:string = this.foodType
  private page:number = 1
  private pageSize:number = 4

  resetFilter () {
    this.foodServices = []
    this.page = 1
    this.infiniteId = this.foodType
  }

  getFoodServices (foodType:string, page:number, pageSize:number) {
    return this.$store.getters.getFoodServiceByFoodType(foodType, page, pageSize)
  }

  populateFoodProducts (state:any) {
    FoodServiceService.getByFoodType(this.foodType, this.page, this.pageSize)
      .then(response => {
        let foodServicesLoaded:FoodService[] = response.data

        if (foodServicesLoaded.length) {
          this.foodServices.push(...foodServicesLoaded)
          state.loaded()
          this.page += 1
        } else {
          state.complete()
        }
      })
  }
}
</script>

<style scoped>
.image{
  width: 250px;
}
</style>
