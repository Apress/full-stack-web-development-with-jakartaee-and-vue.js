<template>
<div>
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
          <label for="image">Image</label>
           <file-upload
            class="btn btn-primary"
            extensions="gif,jpg,jpeg,png,webp"
            accept="image/png,image/gif,image/jpeg,image/webp"
            :size="1024 * 1024 * 10"
            v-model="files"
            @input-file="inputFile"
            ref="upload">
            <i class="fa fa-plus"></i>
            Select file
          </file-upload>
          <div>
            <img class="image img-fluid img-thumbnail" :src="image"/>
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
  private files:any [] = []
  private image: any = new Image()

  save () {
    if (this.isValid(this.foodService)) {
      this.$emit('foodServiceFilled', this.foodService)
    }
  }

  isValid (foodService:FoodService) {
    let valid = true

    if (foodService.name === '') {
      this.$toasted.error(`Name is required`)

      valid = false
    }

    if (foodService.email === '') {
      this.$toasted.error(`Email is required`)

      valid = false
    }

    if (foodService.deliveryFee === 0.0) {
      this.$toasted.error(`Delivery fee is required`)

      valid = false
    }

    if (foodService.foodType === '') {
      this.$toasted.error(`Food type is required`)

      valid = false
    }

    if (this.files.length !== 1) {
      this.$toasted.error(`Image is required`)

      valid = false
    }

    foodService.user.email = foodService.email
    foodService.image = this.files[0].file

    return valid
  }

  inputFile (newFile:any, oldFile:any) {
    if (newFile && !oldFile) {
      let reader = new FileReader()

      reader.onload = (e:any) => {
        this.image = e.target.result
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
