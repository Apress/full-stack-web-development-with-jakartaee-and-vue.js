<template>
  <div>
    <FoodProductForm v-on:foodProductFilled="save($event)" :foodService="foodService"></FoodProductForm>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
import FoodProductForm from '@/components/foodProduct/FoodProductForm.vue'
import { FoodProduct } from '../../entities/FoodProduct'

@Component({
  components: {
    FoodProductForm
  }
})
export default class FoodProductNew extends Vue {
  @Prop() private readonly foodService!: string

  save (foodProduct:FoodProduct) {
    this.$store.commit('saveFoodProduct', foodProduct)

    this.$router.push({ name: 'food_service_view', params: { email: this.foodService } })
  }
}
</script>
