<template>
  <div>
    <FoodProductForm v-on:foodProductFilled="update($event)" :foodProduct="foodProduct"  :foodService="foodProduct.foodService" :type="type"></FoodProductForm>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import FoodProductForm from '@/components/foodProduct/FoodProductForm.vue'
import { FoodProduct } from '../../entities/FoodProduct'

@Component({
  components: {
    FoodProductForm
  }
})
export default class FoodProductUpdate extends Vue {
  @Prop() private readonly id!: number

  private foodProduct:FoodProduct = FoodProduct.emptyFoodProduct()
  private type:string = 'update'

  mounted () {
    this.getFoodProduct(this.id)
  }

  getFoodProduct (id:number) {
    let foodProductToUpdate:FoodProduct = this.$store.getters.getFoodProductById(id)

    this.foodProduct.copyFoodProduct(foodProductToUpdate)
  }

  update (foodProduct:FoodProduct) {
    this.$store.commit('updateFoodProduct', foodProduct)

    this.$router.push({ name: 'food_service_view', params: { email: foodProduct.foodService } })
  }
}
</script>
