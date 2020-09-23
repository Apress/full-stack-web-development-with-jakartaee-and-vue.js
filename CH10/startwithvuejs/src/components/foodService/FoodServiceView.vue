<template>
  <div>
    <div class="row">
      <div class="col-sm">
        <div class="form-group">
          <div>
            <img class="image img-fluid img-thumbnail mx-auto d-block" :src="foodService.imageUrl"/>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-sm">
        <div class="form-group">
          <label for="email">Email</label>
          <input
            v-model="foodService.email"
            type="text"
            class="form-control"
            id="email"
            placeholder="email"
            disabled
          />
        </div>
      </div>
      <div class="col-sm">
        <div class="form-group">
          <label for="name">Name</label>
          <input
            v-model="foodService.name"
            type="text"
            class="form-control"
            id="name"
            placeholder="Name"
            disabled
          />
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-sm">
        <div class="form-group">
          <label for="address">address</label>
          <input
            v-model="foodService.address"
            type="text"
            class="form-control"
            id="address"
            placeholder="address"
            disabled
          />
        </div>
      </div>
      <div class="col-sm">
        <div class="form-group">
          <label for="foodType">Type of Food</label>
          <select class="custom-select" id="foodType" v-model="foodService.foodType" disabled>
            <option selected value="PIZZA">PIZZA</option>
            <option value="CHINESE">CHINESE</option>
          </select>
        </div>
      </div>
    </div>
    <div class="row">
        <div class="col-sm">
          <div class="form-group">
            <label for="deliveryFee">Fee for Delivery</label>
            <input
              v-model="foodService.deliveryFee"
              type="text"
              class="form-control"
              id="deliveryFee"
              placeholder="deliveryFee"
              disabled
            />
          </div>
        </div>
        <div class="col-sm offset-sm">
        </div>
      </div>
    <div class="row">
        <div class="col-sm text-right">
        <router-link  class="btn btn-primary"
          :to="{ name: 'food_service_update', params: { id: id }}"
        >Update</router-link>
    </div>
    </div>
    <div class="row">
      <div class="col-sm">
        <FoodProductList :foodService="id"></FoodProductList>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { FoodService } from '../../entities/FoodService'
import FoodProductList from '@/components/foodProduct/FoodProductList.vue'
import { FoodServiceService } from '../../services/FoodServiceService'

@Component({
  components: {
    FoodProductList
  }
})
export default class FoodServiceView extends Vue {
  @Prop() private readonly id!: string

  private foodService: FoodService = FoodService.emptyFoodService()

  mounted () {
    this.getFoodService(this.id)
  }

  getFoodService (id: string) {
    FoodServiceService.getById(id)
      .then(response => {
        this.foodService = response.data
      })
  }
}
</script>

<style scoped>
.image{
  width: 250px;
}
</style>
