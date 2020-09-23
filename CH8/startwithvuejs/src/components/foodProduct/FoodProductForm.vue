<template>
<div>
  <div class="row">
    <div class="col-sm">
        <div class="form-group" v-if="updateMode">
          <label for="id">Id</label>
          <input
            v-model="foodProduct.id"
            type="text"
            class="form-control"
            id="id"
            placeholder="Id"
            required
          >
        </div>
        <div class="form-group">
          <label for="name">Name</label>
          <input
            v-model="foodProduct.name"
            type="text"
            class="form-control"
            id="name"
            placeholder="Name"
            required
          >
        </div>
        <div class="form-group">
          <label for="description">Description</label>
          <input
            v-model="foodProduct.description"
            type="text"
            class="form-control"
            id="description"
            placeholder="Description"
            required
          >
        </div>
        <div class="form-group">
          <label for="price">Price</label>
          <input
            v-model="foodProduct.price"
            type="text"
            class="form-control"
            id="price"
            placeholder="Price"
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
          <div class="image">
            <img class="img-fluid img-thumbnail" :src="image"/>
          </div>
        </div>
        <div class="form-group">
          <label for="foodService">Food Service</label>
          <input
            v-model="foodService"
            type="text"
            class="form-control"
            id="foodService"
            placeholder="foodService"
            readonly
          >
        </div>
        <button class="btn btn-primary" v-on:click="save">{{updateMode ? 'Update' : 'Save'}}</button>
      </div>
    </div>
</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { FoodProduct } from '../../entities/FoodProduct'
import VueUploadComponent from 'vue-upload-component'

@Component({
  components: {
    'file-upload': VueUploadComponent
  }
})
export default class FoodProductForm extends Vue {
  @Prop() private readonly type!: string
  @Prop() private readonly foodService!: string
  @Prop({ default: () => FoodProduct.emptyFoodProduct() }) private readonly foodProduct!: FoodProduct
  private files:any [] = []
  private image: any = new Image()

  save () {
    if (this.isValid(this.foodProduct)) {
      this.foodProduct.foodService = this.foodService
      this.$emit('foodProductFilled', this.foodProduct)
    }
  }

  isValid (foodProduct:FoodProduct) {
    let valid = true

    if (foodProduct.name === '') {
      this.$toasted.error(`Name is required`)

      valid = false
    }

    if (foodProduct.description === '') {
      this.$toasted.error(`Description is required`)

      valid = false
    }

    if (foodProduct.price === 0.0) {
      this.$toasted.error(`Price is required`)

      valid = false
    }

    if (this.files.length !== 1) {
      this.$toasted.error(`Image is required`)

      valid = false
    }

    foodProduct.image = this.files[0].file

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
