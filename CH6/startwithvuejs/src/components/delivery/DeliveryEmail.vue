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
        <button class="btn btn-primary" v-on:click="setDeliveryEmail">Set Delivery Email</button>
      </div>
    </div>
</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'

@Component
export default class DeliveryEmail extends Vue {
  private email:string = ''
  private errorMessage:string = ''

  setDeliveryEmail () {
    if (this.email === '') {
      this.errorMessage = 'The email is required to set your delivery'
      return
    }

    this.$store.commit('setCurrentDeliveryEmail', this.email)
    this.$router.push({ name: 'food_service_list', params: { email: this.email } })
  }
}
</script>
