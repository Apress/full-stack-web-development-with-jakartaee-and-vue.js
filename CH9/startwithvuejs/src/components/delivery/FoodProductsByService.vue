<template>
  <div>
    <h2 class="text-center">Food Service</h2>
    <div class="row">
      <div class="col-sm">
        <div class="form-group">
          <label for="name">Name</label>
          <input
            v-model="foodServiceData.name"
            type="text"
            class="form-control"
            id="name"
            placeholder="Name"
            disabled
          />
        </div>
      </div>
      <div class="col-sm">
          <div class="form-group">
            <label for="deliveryFee">Fee for Delivery</label>
            <input
              v-model="foodServiceData.deliveryFee"
              type="text"
              class="form-control"
              id="deliveryFee"
              placeholder="deliveryFee"
              disabled
            />
          </div>
        </div>
    </div>
    <h2 class="text-center">Food Products</h2>
    <div class="row mt-2" v-if="items.length>0">
      <div class="col-sm">
        <table class="table  table-dark">
          <thead>
            <tr>
              <th></th>
              <th>Id</th>
              <th>Name</th>
              <th>Description</th>
              <th>Price</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in items" v-bind:key="item.foodProduct.id">
              <td><img class="image img-fluid img-thumbnail" :src="item.foodProduct.imageUrl"/></td>
              <td>{{item.foodProduct.id}}</td>
              <td>{{item.foodProduct.name}}</td>
              <td>{{item.foodProduct.description}}</td>
              <td>{{item.foodProduct.price}}</td>
              <td>
                 <div class="input-group w-50">
                      <input
                          v-model="item.amount"
                          type="number"
                          class="form-control"
                          id="amount"
                          placeholder="amount"
                        >
                    <div class="input-group-append">
                      <a v-on:click="addToCart(item)" class="btn btn-outline-primary">Add</a>
                  </div>
                </div>
              </td>
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
import { FoodService } from '../../entities/FoodService'
import { Item } from '../../entities/Item'
import { FoodProductService } from '../../services/FoodProductService'
import { FoodServiceService } from '../../services/FoodServiceService'

@Component({
  components: {
    InfiniteLoading
  }
})
export default class FoodProductsByService extends Vue {
  @Prop() private readonly foodService!: string
  private items:Item[] = []
  private foodServiceData: FoodService = FoodService.emptyFoodService()
  private page:number = 1
  private pageSize:number = 4

  mounted () {
    FoodServiceService.getById(this.foodService)
      .then(response => {
        this.foodServiceData = response.data
      })
  }

  populateFoodProducts (state:any) {
    FoodProductService.getByFoodService(this.foodService, this.page, this.pageSize)
      .then(response => {
        let foodProductsLoaded:FoodProduct[] = response.data

        if (foodProductsLoaded.length) {
          this.items.push(...foodProductsLoaded.map((foodProduct: FoodProduct) => Item.newItem(foodProduct, 0)))
          state.loaded()
          this.page += 1
        } else {
          state.complete()
        }
      })
  }
  addToCart (item: Item) {
    this.$store.commit('saveItemToCart', item)

    this.$toasted.info(`Item added successfully`)

    this.$router.push({ name: 'cart', params: { foodService: this.foodService } })
  }
}
</script>

<style scoped>
.image{
  width: 250px;
}
</style>
