<template>
<div>
  <div class="row">
    <div class="col-sm">
        <label>State: {{delivery.state}}</label>
    </div>
    <div class="col-sm">
        <label>Address: {{delivery.address}}</label>
    </div>
  </div>
  <div class="row">
    <div class="col-sm">
        <label>Phone: {{delivery.phone}}</label>
    </div>
    <div class="col-sm">
        <label>Total + Fee: {{delivery.total}}</label>
    </div>
  </div>
</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { Delivery } from '../../entities/Delivery'

@Component
export default class DeliverySummary extends Vue {
  @Prop() private readonly email!: string
  private delivery: Delivery = Delivery.emptyDelivery()

  // lifecycle hook
  mounted () {
    this.getDelivery()
  }

  getDelivery () {
    this.delivery = this.$store.getters.getDeliveryByEmail(this.email)
  }
}
</script>
