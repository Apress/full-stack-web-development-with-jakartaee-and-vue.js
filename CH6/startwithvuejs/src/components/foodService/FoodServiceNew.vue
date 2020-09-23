<template>
  <div>
    <FoodServiceForm v-on:foodServiceFilled="save($event)"></FoodServiceForm>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import FoodServiceForm from '@/components/foodService/FoodServiceForm.vue'
import { FoodService } from '../../entities/FoodService'

@Component({
  components: {
    FoodServiceForm
  }
})
export default class FoodServiceNew extends Vue {
  save (foodService:FoodService) {
    this.$store.commit('saveFoodService', foodService)

    this.$router.push({ name: 'login' })
  }
}
</script>
