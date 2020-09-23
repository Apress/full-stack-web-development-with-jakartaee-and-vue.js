<template>
  <div>
    <h2 class="text-center">Food Products</h2>
    <div class="row">
      <div class="col-sm">
       <router-link :to="{ name: 'food_product_new', params: { foodService: foodService }}" class="btn btn-primary">Create New FoodProduct</router-link>
      </div>
    </div>
    <div class="row mt-2" v-if="foodProducts.length>0">
      <div class="col-sm">
        <table class="table  table-dark">
          <thead>
            <tr>
              <th></th>
              <th>Id</th>
              <th>Name</th>
              <th>Description</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="foodProduct in foodProducts" v-bind:key="foodProduct.id">
              <td><img class="image img-fluid img-thumbnail" :src="foodProduct.imageUrl"/></td>
              <td>{{foodProduct.id}}</td>
              <td>{{foodProduct.name}}</td>
              <td>{{foodProduct.description}}</td>
              <td>{{foodProduct.price}}</td>
              <td><router-link  :to="{ name: 'food_product_update', params: { id: foodProduct.id }}">Update</router-link></td>
              <td><a v-on:click="remove(foodProduct)" href="#">Delete</a></td>
            </tr>
          </tbody>
        </table>
    </div>
    </div>
    <infinite-loading @infinite="populateFoodProducts"></infinite-loading>
  </div>
</template>

<script lang="ts">
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import InfiniteLoading from 'vue-infinite-loading'
import { Component, Vue, Prop } from 'vue-property-decorator'
import { FoodProduct } from '../../entities/FoodProduct'
import { FoodProductService } from '../../services/FoodProductService'

@Component({
  components: {
    InfiniteLoading
  }
})
export default class FoodProductList extends Vue {
  @Prop() private readonly foodService!: string
  private foodProducts:FoodProduct[] = []
  private page:number = 1
  private pageSize:number = 4

  populateFoodProducts (state:any) {
    FoodProductService.getByFoodService(this.foodService, this.page, this.pageSize)
      .then(response => {
        let foodProductsLoaded:FoodProduct[] = response.data

        if (foodProductsLoaded.length) {
          this.foodProducts.push(...foodProductsLoaded)
          state.loaded()
          this.page += 1
        } else {
          state.complete()
        }
      })
  }

  remove (foodProductToRemove:FoodProduct) {
    FoodProductService.deActivate(foodProductToRemove)
      .then(response => {
        const index = this.foodProducts.findIndex(foodProduct => foodProduct.id === foodProductToRemove.id)

        Vue.delete(this.foodProducts, index)

        this.$toasted.info(`Delete successfully`)
      })
  }
}
</script>

<style scoped>
.image{
  width: 250px;
}
</style>
