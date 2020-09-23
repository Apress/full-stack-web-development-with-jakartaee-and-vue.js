<template>
<div>
  <div class="row">
    <div class="col-sm">
      <div v-if="errorMessage" class="alert alert-danger" role="alert">
        {{errorMessage}}
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-sm">
        <div class="form-group">
          <label for="email">Email</label>
          <input
            v-model="email"
            type="text"
            class="form-control"
            id="email"
            placeholder="email"
            required
          >
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            v-model="password"
            type="password"
            class="form-control"
            id="password"
            placeholder="Password"
            required
          >
        </div>
        <button class="btn btn-primary" v-on:click="login">Login</button>
        <router-link :to="{ name: 'food_service_new'}"> Or Create an Account</router-link>
      </div>
    </div>
</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { FoodService } from '../../entities/FoodService'

@Component
export default class LoginForm extends Vue {
  private email:string = ''
  private password:string = ''
  private errorMessage:string = ''

  login () {
    let foodService:FoodService = this.$store.getters.getFoodServiceByEmailAndPassword(this.email, this.password)

    if (foodService) {
      this.$store.commit('setCurrentFoodServiceLoggedIn', foodService.email)
      this.$router.push({ name: 'food_service_view', params: { email: this.email } })
    } else {
      this.errorMessage = 'Email or Password are wrong'
    }
  }
}
</script>
