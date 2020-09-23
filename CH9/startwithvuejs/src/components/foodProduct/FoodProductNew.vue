<template>
  <div>
    <FoodProductForm v-on:foodProductFilled="save($event)" :foodService="foodService"></FoodProductForm>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
import FoodProductForm from '@/components/foodProduct/FoodProductForm.vue'
import { FoodProduct } from '../../entities/FoodProduct'
import { FoodProductService } from '../../services/FoodProductService'

@Component({
  components: {
    FoodProductForm
  }
})
export default class FoodProductNew extends Vue {
  @Prop() private readonly foodService!: string

  save (foodProduct:FoodProduct) {
    foodProduct.active = true

    FoodProductService.create(foodProduct)
      .then(response => {
        this.$toasted.info(`Save successfully`)

        this.$router.push({ name: 'food_service_view', params: { id: this.foodService } })
      })
  }
}
</script>
