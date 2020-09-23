<template>
  <div>
    <FoodServiceForm v-on:foodServiceFilled="save($event)"></FoodServiceForm>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import FoodServiceForm from '@/components/foodService/FoodServiceForm.vue'
import { FoodService } from '../../entities/FoodService'
import { FoodServiceService } from '../../services/FoodServiceService'

@Component({
  components: {
    FoodServiceForm
  }
})
export default class FoodServiceNew extends Vue {
  save (foodService:FoodService) {
    foodService.active = true

    FoodServiceService.create(foodService)
      .then(response => {
        this.$toasted.info(`Save successfully`)

        this.$router.push({ name: 'login' })
      })
  }
}
</script>
