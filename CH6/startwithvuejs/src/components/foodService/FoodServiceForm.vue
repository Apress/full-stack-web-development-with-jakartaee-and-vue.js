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
            v-model="foodService.email"
            type="text"
            class="form-control"
            id="email"
            placeholder="email"
            :readonly="updateMode"
            required
          >
        </div>
        <div class="form-group">
          <label for="name">Name</label>
          <input
            v-model="foodService.name"
            type="text"
            class="form-control"
            id="name"
            placeholder="Name"
            required
          >
        </div>
        <div class="form-group">
          <label for="address">Address</label>
          <input
            v-model="foodService.address"
            type="text"
            class="form-control"
            id="address"
            placeholder="address"
            required
          >
        </div>
        <div class="form-group">
          <label for="foodType">Food Type</label>
          <select class="custom-select" id="foodType" v-model="foodService.foodType">
            <option selected value="PIZZA">PIZZA</option>
            <option value="CHINESE">CHINESE</option>
          </select>
        </div>
        <div class="form-group">
          <label for="deliveryFee">Delivery Fee</label>
          <input
            v-model="foodService.deliveryFee"
            type="text"
            class="form-control"
            id="deliveryFee"
            placeholder="deliveryFee"
            required
          >
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            v-model="foodService.password"
            type="password"
            class="form-control"
            id="password"
            placeholder="Password"
            required
          >
        </div>
        <div class="form-group">
          <label for="password">Repeat Password</label>
          <input
            v-model="repeatPassword"
            type="password"
            class="form-control"
            id="repeatPassword"
            placeholder="Password"
            required
          >
        </div>
        <div class="form-group">
          <label for="image">Image</label>
           <file-upload
            class="btn btn-primary"
            extensions="gif,jpg,jpeg,png,webp"
            accept="image/png,image/gif,image/jpeg,image/webp"
            :size="1024 * 1024 * 10"
            @input-file="inputFile"
            ref="upload">
            <i class="fa fa-plus"></i>
            Select file
          </file-upload>
          <div>
            <img class="image img-fluid img-thumbnail" :src="foodService.image"/>
          </div>
        </div>
        <button class="btn btn-primary" v-on:click="save">{{updateMode ? 'Update' : 'Save'}}</button>
      </div>
    </div>
</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { FoodService } from '../../entities/FoodService'
import VueUploadComponent from 'vue-upload-component'

@Component({
  components: {
    'file-upload': VueUploadComponent
  }
})
export default class FoodServiceForm extends Vue {
  @Prop() private readonly type!: string
  @Prop({ default: () => FoodService.emptyFoodService() }) private readonly foodService!: FoodService

  private repeatPassword:string = ''
  private errorMessage:string = ''

  save () {
    if (this.isValid(this.foodService)) {
      this.$emit('foodServiceFilled', this.foodService)
    }
  }

  isValid (foodService:FoodService) {
    if (foodService.name === '') {
      this.errorMessage = 'Name is required'

      return false
    }

    if (foodService.email === '') {
      this.errorMessage = 'Email is required'

      return false
    }

    if (foodService.deliveryFee === 0.0) {
      this.errorMessage = 'Delivery fee is required'

      return false
    }

    if (foodService.foodType === '') {
      this.errorMessage = 'Food type is required'

      return false
    }

    if (foodService.password === '') {
      this.errorMessage = 'Password is required'

      return false
    }

    if (foodService.password !== this.repeatPassword) {
      this.errorMessage = "Passwords don't match"

      return false
    }

    return true
  }

  inputFile (newFile:any, oldFile:any) {
    if (newFile && !oldFile) {
      let reader = new FileReader()

      reader.onload = (e:any) => {
        this.foodService.image = e.target.result
      }
      reader.readAsDataURL(newFile.file)
    }
  }
  get updateMode () {
    return this.type === 'update'
  }
}
</script>

<style scoped>
.image{
  width: 250px;
}
</style>
