<template>
  <div>
    <UserForm v-on:userFilled="save($event)"></UserForm>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import UserForm from '@/components/UserForm.vue'
import { User } from '../entities/User'

@Component({
  components: {
    UserForm
  }
})
export default class UserNew extends Vue {
  save (user:User) {
    this.$store.commit('saveUser', user)

    this.$router.push({ name: 'user_list' })
  }
}
</script>
