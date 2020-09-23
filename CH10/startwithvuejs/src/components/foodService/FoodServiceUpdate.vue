<template>
  <div>
    <FoodServiceForm v-on:foodServiceFilled="update($event)" :foodService="foodService" :type="type"></FoodServiceForm>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import FoodServiceForm from '@/components/foodService/FoodServiceForm.vue'
import { FoodService } from '../../entities/FoodService'
import { FoodServiceService } from '../../services/FoodServiceService'

@Component({
  components: {
    FoodServiceForm
  }
})
export default class FoodServiceUpdate extends Vue {
  @Prop() private readonly id!: string
  private type:string = 'update'

  private foodService:FoodService = FoodService.emptyFoodService()

  mounted () {
    this.getFoodService(this.id)
  }

  getFoodService (id:string) {
    FoodServiceService.getById(id)
      .then(response => {
        this.foodService = response.data
      })
  }

  update (foodService:FoodService) {
    FoodServiceService.update(foodService)
      .then(response => {
        this.$toasted.info(`Update successfully`)

        this.$router.push({ name: 'food_service_view' })
      })
  }
}
</script>
