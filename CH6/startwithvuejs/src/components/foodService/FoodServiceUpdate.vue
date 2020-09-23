<template>
  <div>
    <FoodServiceForm v-on:foodServiceFilled="update($event)" :foodService="foodService" :type="type"></FoodServiceForm>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import FoodServiceForm from '@/components/foodService/FoodServiceForm.vue'
import { FoodService } from '../../entities/FoodService'

@Component({
  components: {
    FoodServiceForm
  }
})
export default class FoodServiceUpdate extends Vue {
  @Prop() private readonly email!: string
  private type:string = 'update'

  private foodService:FoodService = FoodService.emptyFoodService()

  mounted () {
    this.getFoodService(this.email)
  }

  getFoodService (email:string) {
    let foodServiceToUpdate:FoodService = this.$store.getters.getFoodServiceByEmail(email)

    this.foodService.copyFoodService(foodServiceToUpdate)
  }

  update (foodService:FoodService) {
    this.$store.commit('updateFoodService', foodService)

    this.$router.push({ name: 'food_service_view' })
  }
}
</script>
