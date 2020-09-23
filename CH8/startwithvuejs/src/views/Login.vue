<template>
  <div class="container">
    <h1 class="text-center">Login</h1>
    <LoginForm></LoginForm>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import LoginForm from '@/components/login/LoginForm.vue'

@Component({
  components: {
    LoginForm
  }
})
export default class Login extends Vue {
}
</script>
